package com.achmadichzan.dicodingevents.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Parcelize
@Serializable
data class EventResponse(

    @SerialName("listEvents") val listEvents: List<ListEventsItem>? = emptyList(),
    @SerialName("error") val error: Boolean? = null,
    @SerialName("message") val message: String? = null

): Parcelable

@Parcelize
@Serializable
data class ListEventsItem(

    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("mediaCover") val mediaCover: String? = null,
    @SerialName("imageLogo") val imageLogo: String? = null,
    @SerialName("registrants") val registrants: Int? = null,
    @SerialName("quota") val quota: Int? = null,
    @SerialName("category") val category: String? = null,
    @SerialName("cityName") val cityName: String? = null,
    @SerialName("beginTime") val beginTime: String? = null,
    @SerialName("endTime") val endTime: String? = null,
    @SerialName("link") val link: String? = null

): Parcelable
