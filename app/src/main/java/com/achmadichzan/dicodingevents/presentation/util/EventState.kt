package com.achmadichzan.dicodingevents.presentation.util

import com.achmadichzan.dicodingevents.domain.model.Event

sealed class EventState {
    data object Loading : EventState()
    data class SuccessUpcoming(val events: List<Event>) : EventState()
    data class Success(val events: List<Event>) : EventState()
    data class SuccessDetail(val event: Event?) : EventState()
    data class Error(val message: String) : EventState()
}