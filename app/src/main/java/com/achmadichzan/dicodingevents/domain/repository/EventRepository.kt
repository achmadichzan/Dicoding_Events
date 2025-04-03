package com.achmadichzan.dicodingevents.domain.repository

import com.achmadichzan.dicodingevents.domain.model.Event
import com.achmadichzan.dicodingevents.domain.model.EventResponse
import com.achmadichzan.dicodingevents.domain.model.ListEventsItem

interface EventRepository {
    suspend fun getAllEvents(): EventResponse
    suspend fun getEventDetail(eventId: Int): Event?
    suspend fun searchEvents(keyword: String): EventResponse
}