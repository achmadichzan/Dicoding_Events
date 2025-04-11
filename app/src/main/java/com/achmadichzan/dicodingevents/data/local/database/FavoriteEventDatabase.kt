package com.achmadichzan.dicodingevents.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.achmadichzan.dicodingevents.data.local.dao.FavoriteEventDao
import com.achmadichzan.dicodingevents.data.local.entity.FavoriteEventEntity

@Database(entities = [FavoriteEventEntity::class], version = 1, exportSchema = false)
abstract class FavoriteEventDatabase : RoomDatabase() {
    abstract fun favoriteEventDao(): FavoriteEventDao
}
