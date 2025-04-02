package com.achmadichzan.dicodingevents.presentation.util

sealed class EventIntent {
    object LoadAllEvents : EventIntent()
    data class LoadEventDetail(val eventId: Int) : EventIntent()
}