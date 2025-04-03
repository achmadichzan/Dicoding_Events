package com.achmadichzan.dicodingevents.presentation.util

sealed class EventIntent {
    data object LoadAllEvents : EventIntent()
    data class LoadEventDetail(val eventId: Int) : EventIntent()
}