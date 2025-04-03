package com.achmadichzan.dicodingevents.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class DetailEventResponse(

	@SerialName("error") val error: Boolean? = null,
	@SerialName("message") val message: String? = null,
	@SerialName("event") val event: Event? = null
)

@Serializable
data class Event(

	@SerialName("summary") val summary: String? = null,
	@SerialName("mediaCover") val mediaCover: String? = null,
	@SerialName("registrants") val registrants: Int? = null,
	@SerialName("imageLogo") val imageLogo: String? = null,
	@SerialName("link") val link: String? = null,
	@SerialName("description") val description: String? = null,
	@SerialName("ownerName") val ownerName: String? = null,
	@SerialName("cityName") val cityName: String? = null,
	@SerialName("quota") val quota: Int? = null,
	@SerialName("name") val name: String? = null,
	@SerialName("id") val id: Int? = null,
	@SerialName("beginTime") val beginTime: String? = null,
	@SerialName("endTime") val endTime: String? = null,
	@SerialName("category") val category: String? = null
)
