package com.achmadichzan.dicodingevents.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Upsert
    suspend fun upsertEvents(events: List<EventEntity>)

    @Query("SELECT * FROM events ORDER BY id DESC")
    fun getAllEvents(): Flow<List<EventEntity>>

    @Query("SELECT * FROM events WHERE id = :id")
    fun getEventById(id: Int): Flow<EventEntity?>
}
