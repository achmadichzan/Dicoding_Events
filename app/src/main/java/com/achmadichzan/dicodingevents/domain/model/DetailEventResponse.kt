package com.achmadichzan.dicodingevents.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Parcelize
@Serializable
data class DetailEventResponse(

	@SerialName("event") val event: Event? = null,
	@SerialName("message") val message: String? = null,
	@SerialName("error") val error: Boolean? = null

): Parcelable