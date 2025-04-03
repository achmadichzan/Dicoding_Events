package com.achmadichzan.dicodingevents.presentation.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.achmadichzan.dicodingevents.domain.repository.EventRepository
import com.achmadichzan.dicodingevents.presentation.util.EventIntent
import com.achmadichzan.dicodingevents.presentation.util.EventState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val repository: EventRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow<EventState>(
        savedStateHandle.get<EventState>("eventState") ?: EventState.Loading
    )
    val state: StateFlow<EventState> = _state

    private val _searchQuery = MutableStateFlow("")

    init {
        observeSearchQuery()
    }

    fun handleIntent(intent: EventIntent) {
        when (intent) {
            is EventIntent.LoadAllEvents -> fetchAllEvents()
            is EventIntent.LoadEventDetail -> fetchEventDetail(intent.eventId)
            is EventIntent.SearchEvents -> _searchQuery.update { intent.keyword }
        }
    }

    private fun fetchAllEvents() {
        viewModelScope.launch {
            if (savedStateHandle.contains("eventList")) {
                _state.update { EventState.Success(savedStateHandle["eventList"]!!) }
                return@launch
            }

            _state.update { EventState.Loading }
            try {
                val response = repository.getAllEvents()
                savedStateHandle["eventList"] = response.listEvents ?: emptyList()
                _state.update { EventState.Success(response.listEvents ?: emptyList()) }
            } catch (e: Exception) {
                _state.update { EventState.Error(e.localizedMessage ?: "Unknown error") }
            }
        }
    }

    private fun fetchEventDetail(eventId: Int) {
        viewModelScope.launch {
            if (savedStateHandle.contains("event_$eventId")) {
                _state.update { EventState.SuccessDetail(savedStateHandle["event_$eventId"]!!) }
                return@launch
            }

            _state.update { EventState.Loading }
            try {
                val event = repository.getEventDetail(eventId)
                savedStateHandle["event_$eventId"] = event
                _state.update { EventState.SuccessDetail(event) }
            } catch (e: Exception) {
                _state.update { EventState.Error(e.localizedMessage ?: "Unknown error") }
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        viewModelScope.launch {
            _searchQuery
                .debounce(1_000)
                .distinctUntilChanged()
                .collectLatest { keyword ->
                    if (keyword.isNotBlank()) {
                        searchEvents(keyword)
                    } else {
                        fetchAllEvents()
                    }
                }
        }
    }

    private suspend fun searchEvents(keyword: String) {
        _state.update { EventState.Loading }
        try {
            val response = repository.searchEvents(keyword)
            _state.update { EventState.Success(response.listEvents ?: emptyList()) }
        } catch (e: Exception) {
            _state.update { EventState.Error(e.localizedMessage ?: "Unknown error") }
        }
    }
}
