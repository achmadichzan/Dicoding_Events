package com.achmadichzan.dicodingevents.data.repository

import com.achmadichzan.dicodingevents.data.local.dao.EventDao
import com.achmadichzan.dicodingevents.data.local.dao.FavoriteEventDao
import com.achmadichzan.dicodingevents.data.local.entity.EventEntity
import com.achmadichzan.dicodingevents.data.local.entity.FavoriteEventEntity
import com.achmadichzan.dicodingevents.data.local.mapper.toEntity
import com.achmadichzan.dicodingevents.data.network.EventApiService
import com.achmadichzan.dicodingevents.data.preferences.DarkThemePreferences
import com.achmadichzan.dicodingevents.domain.model.Event
import com.achmadichzan.dicodingevents.domain.model.EventResponse
import com.achmadichzan.dicodingevents.domain.repository.EventRepository
import com.achmadichzan.dicodingevents.presentation.util.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EventRepositoryImpl(
    private val apiService: EventApiService,
    private val eventDao: EventDao,
    private val favoriteEventDao: FavoriteEventDao,
    private val preferences: DarkThemePreferences
) : EventRepository {

    override suspend fun getUpcomingEvents(): DataResult<EventResponse> {
        return try {
            val response = apiService.getUpcomingEvents()
            response.listEvents?.let { events ->
                eventDao.upsertEvents(events.map { it.toEntity() })
            }
            DataResult.Success(response)
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }

    override suspend fun getAllEvents(): DataResult<EventResponse> {
        return try {
            val response = apiService.getAllEvents()
            response.listEvents?.let { events ->
                eventDao.upsertEvents(events.map { it.toEntity() })
            }
            DataResult.Success(response)
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }

    override suspend fun getEventDetail(eventId: Int): DataResult<Event> {
        return try {
            val response = apiService.getEventDetail(eventId)
            response.event?.let { event ->
                eventDao.upsertEvents(listOf(event.toEntity()))
                DataResult.Success(event)
            } ?: throw Exception("Event not found")
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }

    override suspend fun searchEvents(keyword: String): DataResult<EventResponse> {
        return try {
            val response = apiService.searchEvents(keyword)
            DataResult.Success(response)
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }

    override fun getAllEventsFlow(): Flow<List<EventEntity>> {
        return eventDao.getAllEvents()
    }

    override fun getEventById(id: Int): Flow<EventEntity?> {
        return eventDao.getEventById(id)
    }

    override suspend fun addToFavorite(event: FavoriteEventEntity) {
        favoriteEventDao.upsertFavoriteEvent(event)
    }

    override fun getAllFavorites(): Flow<List<FavoriteEventEntity>?> {
        return favoriteEventDao.getAllFavorites()
    }

    override fun isFavorite(eventId: Int): Flow<Boolean> {
        return favoriteEventDao.getFavoriteEventById(eventId).map { it != null }
    }

    override suspend fun removeFromFavorite(event: FavoriteEventEntity) {
        favoriteEventDao.deleteFavorite(event)
    }

    override fun getThemeSetting(): Flow<Boolean> = preferences.getThemeSetting()

    override suspend fun saveThemeSetting(isDark: Boolean) {
        preferences.saveThemeSetting(isDark)
    }
}
