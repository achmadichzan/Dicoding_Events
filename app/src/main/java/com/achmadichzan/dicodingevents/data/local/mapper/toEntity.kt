package com.achmadichzan.dicodingevents.data.local.mapper

import com.achmadichzan.dicodingevents.data.local.EventEntity
import com.achmadichzan.dicodingevents.domain.model.Event

fun Event.toEntity(): EventEntity {
    return EventEntity(
        id = this.id,
        name = this.name,
        summary = this.summary,
        mediaCover = this.mediaCover,
        registrants = this.registrants,
        imageLogo = this.imageLogo,
        link = this.link,
        description = this.description,
        ownerName = this.ownerName,
        cityName = this.cityName,
        quota = this.quota,
        beginTime = this.beginTime,
        endTime = this.endTime,
        category = this.category
    )
}
