package com.achmadichzan.dicodingevents.data.repository

import com.achmadichzan.dicodingevents.data.network.EventApiService
import com.achmadichzan.dicodingevents.domain.model.Event
import com.achmadichzan.dicodingevents.domain.model.EventResponse
import com.achmadichzan.dicodingevents.domain.model.ListEventsItem
import com.achmadichzan.dicodingevents.domain.repository.EventRepository

class EventRepositoryImpl(private val apiService: EventApiService) : EventRepository {
    override suspend fun getAllEvents(): EventResponse {
        return apiService.getAllEvents()
    }

    override suspend fun getEventDetail(eventId: Int): Event? {
        val response = apiService.getEventDetail(eventId)
        return response.event ?: throw Exception("Event not found")
    }

    override suspend fun searchEvents(keyword: String): EventResponse {
        return apiService.searchEvents(keyword)
    }
}