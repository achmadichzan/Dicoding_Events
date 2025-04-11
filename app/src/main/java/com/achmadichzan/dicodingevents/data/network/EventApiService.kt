package com.achmadichzan.dicodingevents.data.network

import com.achmadichzan.dicodingevents.domain.model.DetailEventResponse
import com.achmadichzan.dicodingevents.domain.model.EventResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class EventApiService(private val client: HttpClient) {

    suspend fun getUpcomingEvents(): EventResponse {
        return client.get("events?active=1").body()
    }

    suspend fun getAllEvents(): EventResponse {
        return client.get("events").body()
    }

    suspend fun getEventDetail(eventId: Int): DetailEventResponse {
        return client.get("events/$eventId").body()
    }

    suspend fun searchEvents(keyword: String): EventResponse {
        val url = "events?active=-1&q=$keyword"
        return client.get(url).body()
    }

    suspend fun getReminderEvents(): EventResponse {
        return client.get("events?active=-1&limit=1").body()
    }
}