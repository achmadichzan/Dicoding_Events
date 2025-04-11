package com.achmadichzan.dicodingevents.domain.repository

import com.achmadichzan.dicodingevents.data.local.entity.EventEntity
import com.achmadichzan.dicodingevents.data.local.entity.FavoriteEventEntity
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
    fun getEventById(id: Int): Flow<EventEntity?>

    // 🔹 Local - Room
    suspend fun addToFavorite(event: FavoriteEventEntity)
    fun getAllFavorites(): Flow<List<FavoriteEventEntity>?>
    fun isFavorite(eventId: Int): Flow<Boolean>
    suspend fun removeFromFavorite(event: FavoriteEventEntity)

    // 🔹 Local - DataStore
    fun getThemeSetting(): Flow<Boolean>
    suspend fun saveThemeSetting(isDarkModeActive: Boolean)

    // 🔹 WorkManager
    suspend fun getReminderEvent(): EventResponse?
}
