package com.achmadichzan.dicodingevents.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite_events")
data class FavoriteEventEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val name: String? = null,
    val imageLogo: String? = null,
    val beginTime: String? = null,
): Parcelable
