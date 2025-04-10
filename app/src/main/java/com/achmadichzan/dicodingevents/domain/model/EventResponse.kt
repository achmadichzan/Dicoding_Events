package com.achmadichzan.dicodingevents.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Parcelize
@Serializable
data class EventResponse(

    @SerialName("listEvents") val listEvents: List<Event>? = emptyList(),
    @SerialName("error") val error: Boolean? = null,
    @SerialName("message") val message: String? = null

): Parcelable