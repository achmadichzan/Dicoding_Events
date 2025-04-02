package com.achmadichzan.dicodingevents.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object Route {

    @Serializable
    object EventList

    @Serializable
    data class EventDetail(val eventId: Int)
}