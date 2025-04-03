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

@Parcelize
@Serializable
data class Event(

	@SerialName("id") val id: Int = 0,
	@SerialName("name") val name: String? = null,
	@SerialName("summary") val summary: String? = null,
	@SerialName("mediaCover") val mediaCover: String? = null,
	@SerialName("registrants") val registrants: Int? = null,
	@SerialName("imageLogo") val imageLogo: String? = null,
	@SerialName("link") val link: String? = null,
	@SerialName("description") val description: String? = null,
	@SerialName("ownerName") val ownerName: String? = null,
	@SerialName("cityName") val cityName: String? = null,
	@SerialName("quota") val quota: Int? = null,
	@SerialName("beginTime") val beginTime: String? = null,
	@SerialName("endTime") val endTime: String? = null,
	@SerialName("category") val category: String? = null

): Parcelable
