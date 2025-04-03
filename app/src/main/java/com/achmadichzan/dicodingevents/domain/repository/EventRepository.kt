package com.achmadichzan.dicodingevents.domain.repository

import com.achmadichzan.dicodingevents.domain.model.Event
import com.achmadichzan.dicodingevents.domain.model.EventResponse

interface EventRepository {
    suspend fun getAllEvents(): EventResponse
    suspend fun getEventDetail(eventId: Int): Event?
}