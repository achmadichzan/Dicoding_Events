package com.achmadichzan.dicodingevents.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.achmadichzan.dicodingevents.data.local.entity.FavoriteEventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteEventDao {
    @Upsert
    suspend fun upsertFavoriteEvent(favoriteEvent: FavoriteEventEntity)

    @Query("SELECT * FROM favorite_events")
    fun getAllFavorites(): Flow<List<FavoriteEventEntity>?>

    @Query("SELECT * FROM favorite_events WHERE id = :eventId")
    fun getFavoriteEventById(eventId: Int): Flow<FavoriteEventEntity?>

    @Delete
    suspend fun deleteFavorite(event: FavoriteEventEntity)
}