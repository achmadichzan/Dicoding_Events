package com.achmadichzan.dicodingevents.data.repository

import com.achmadichzan.dicodingevents.data.network.EventApiService
import com.achmadichzan.dicodingevents.domain.model.EventResponse
import com.achmadichzan.dicodingevents.domain.model.ListEventsItem
import com.achmadichzan.dicodingevents.domain.repository.EventRepository

class EventRepositoryImpl(private val apiService: EventApiService) : EventRepository {
    override suspend fun getAllEvents(): EventResponse {
        return apiService.getAllEvents()
    }

    override suspend fun getEventDetail(eventId: Int): ListEventsItem {
        println("Fetching event detail for ID: $eventId")
        return apiService.getEventDetail(eventId).listEvents?.find { eventId == it.id } ?: ListEventsItem(id = 9217)
    }
}