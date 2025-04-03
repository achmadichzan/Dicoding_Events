package com.achmadichzan.dicodingevents.presentation.util

import com.achmadichzan.dicodingevents.domain.model.ListEventsItem

sealed class EventState {
    data object Loading : EventState()
    data class Success(val events: List<ListEventsItem>) : EventState()
    data class SuccessDetail(val event: ListEventsItem) : EventState()
    data class Error(val message: String) : EventState()
}