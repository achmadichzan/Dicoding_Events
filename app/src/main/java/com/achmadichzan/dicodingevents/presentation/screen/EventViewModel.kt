package com.achmadichzan.dicodingevents.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.achmadichzan.dicodingevents.data.local.mapper.toDomain
import com.achmadichzan.dicodingevents.domain.repository.EventRepository
import com.achmadichzan.dicodingevents.presentation.util.DataResult
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
    private val repository: EventRepository
) : ViewModel() {

    private val _state = MutableStateFlow<EventState>(EventState.Loading)
    val state: StateFlow<EventState> = _state

    private val _upcomingEvents = MutableStateFlow<EventState>(EventState.Loading)
    val upcomingEvents: StateFlow<EventState> = _upcomingEvents

    private val _searchQuery = MutableStateFlow("")

    fun handleIntent(intent: EventIntent) {
        when (intent) {
            is EventIntent.LoadUpcomingEvents -> fetchUpcomingEvents()
            is EventIntent.LoadAllEvents -> observeSearchQuery()
            is EventIntent.LoadEventDetail -> fetchEventDetail(intent.eventId)
            is EventIntent.SearchEvents -> _searchQuery.update { intent.keyword }
        }
    }

    private fun fetchUpcomingEvents() {
        viewModelScope.launch {
            when (val result = repository.getUpcomingEvents()) {
                is DataResult.Success -> {
                    val list = result.data.listEvents ?: emptyList()
                    _upcomingEvents.update { EventState.SuccessUpcoming(list) }
                }
                is DataResult.Error -> {
                    _upcomingEvents.update { EventState.Error(
                        result.exception.localizedMessage ?: "Unknown error"
                    ) }
                }
            }
        }
    }


    private fun fetchAllEvents() {
        viewModelScope.launch {
            repository.getAllEventsFlow().collect { eventEntities ->
                val events = eventEntities.map { it.toDomain() }
                _state.update { EventState.Success(events) }
            }
        }

        viewModelScope.launch {
            when (val result = repository.getAllEvents()) {
                is DataResult.Success -> {
                    TODO()
                }
                is DataResult.Error -> {
                    _state.update { EventState.Error(
                        result.exception.localizedMessage ?: "Unknown error"
                    ) }
                }
            }
        }
    }

    private fun fetchEventDetail(eventId: Int) {
        viewModelScope.launch {
            repository.getEventById(eventId).collect { eventEntity ->
                if (eventEntity != null) {
                    _state.update { EventState.SuccessDetail(eventEntity.toDomain()) }
                } else {
                    when (val result = repository.getEventDetail(eventId)) {
                        is DataResult.Success -> {
                            _state.update { EventState.SuccessDetail(result.data) }
                        }
                        is DataResult.Error -> {
                            _state.update { EventState.Error(
                                result.exception.localizedMessage ?: "Unknown error"
                            ) }
                        }
                    }
                }
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        viewModelScope.launch {
            _searchQuery
                .debounce(1_500)
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
        when (val result = repository.searchEvents(keyword)) {
            is DataResult.Success -> {
                val list = result.data.listEvents ?: emptyList()
                _state.update { EventState.Success(list) }
            }
            is DataResult.Error -> {
                _state.update { EventState.Error(
                    result.exception.localizedMessage ?: "Unknown error"
                ) }
            }
        }
    }
}
