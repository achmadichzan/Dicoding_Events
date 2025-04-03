package com.achmadichzan.dicodingevents.presentation.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.achmadichzan.dicodingevents.domain.repository.EventRepository
import com.achmadichzan.dicodingevents.presentation.util.EventIntent
import com.achmadichzan.dicodingevents.presentation.util.EventState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    fun handleIntent(intent: EventIntent) {
        when (intent) {
            is EventIntent.LoadAllEvents -> fetchAllEvents()
            is EventIntent.LoadEventDetail -> fetchEventDetail(intent.eventId)
        }
    }

    private fun fetchAllEvents() {
        viewModelScope.launch {
            if (savedStateHandle.contains("eventList")) {
                _state.value = EventState.Success(savedStateHandle["eventList"]!!)
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
                _state.value = EventState.SuccessDetail(savedStateHandle["event_$eventId"]!!)
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
}
