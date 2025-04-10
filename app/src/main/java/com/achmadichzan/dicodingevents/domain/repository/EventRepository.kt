package com.achmadichzan.dicodingevents.domain.repository

import com.achmadichzan.dicodingevents.data.local.EventEntity
import com.achmadichzan.dicodingevents.domain.model.Event
import com.achmadichzan.dicodingevents.domain.model.EventResponse
import com.achmadichzan.dicodingevents.presentation.util.DataResult
import kotlinx.coroutines.flow.Flow

interface EventRepository {

    // 🔹 Remote - API
    suspend fun getUpcomingEvents(): DataResult<EventResponse>
    suspend fun getAllEvents(): DataResult<EventResponse>
    suspend fun getEventDetail(eventId: Int): DataResult<Event>
    suspend fun searchEvents(keyword: String): DataResult<EventResponse>

    // 🔹 Local - Room
    fun getAllEventsFlow(): Flow<List<EventEntity>>
    suspend fun insertEvents(events: List<EventEntity>)
    fun getEventById(id: Int): Flow<EventEntity?>
}
