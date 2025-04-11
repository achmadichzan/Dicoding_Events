package com.achmadichzan.dicodingevents.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.achmadichzan.dicodingevents.data.local.dao.EventDao
import com.achmadichzan.dicodingevents.data.local.entity.EventEntity

@Database(entities = [EventEntity::class], version = 1, exportSchema = false)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}