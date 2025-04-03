package com.achmadichzan.dicodingevents.data.network

import com.achmadichzan.dicodingevents.domain.model.DetailEventResponse
import com.achmadichzan.dicodingevents.domain.model.EventResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class EventApiService(private val client: HttpClient) {

    suspend fun getAllEvents(): EventResponse {
        return client.get("events").body()
    }

    suspend fun getEventDetail(eventId: Int): DetailEventResponse {
        return client.get("events/$eventId").body()
    }

    companion object {
        internal const val BASE_URL = "https://event-api.dicoding.dev/"
    }
}