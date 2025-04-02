package com.achmadichzan.dicodingevents.data.network

import com.achmadichzan.dicodingevents.domain.model.EventResponse
import com.achmadichzan.dicodingevents.domain.model.ListEventsItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class EventApiService(private val client: HttpClient) {
    private val BASE_URL = "https://event-api.dicoding.dev/events"

    suspend fun getAllEvents(): EventResponse {
        return client.get(BASE_URL).body()
    }

    suspend fun getEventDetail(eventId: Int): EventResponse {
        return client.get("$BASE_URL/$eventId").body()
    }
}