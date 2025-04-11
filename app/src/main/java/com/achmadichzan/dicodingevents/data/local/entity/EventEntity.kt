package com.achmadichzan.dicodingevents.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey
    val id: Int,
    val name: String?,
    val summary: String?,
    val mediaCover: String?,
    val registrants: Int?,
    val imageLogo: String?,
    val link: String?,
    val description: String?,
    val ownerName: String?,
    val cityName: String?,
    val quota: Int?,
    val beginTime: String?,
    val endTime: String?,
    val category: String?
) : Parcelable