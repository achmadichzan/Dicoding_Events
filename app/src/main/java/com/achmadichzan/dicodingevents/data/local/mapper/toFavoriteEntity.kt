package com.achmadichzan.dicodingevents.data.local.mapper

import com.achmadichzan.dicodingevents.data.local.entity.FavoriteEventEntity
import com.achmadichzan.dicodingevents.domain.model.Event

fun Event.toFavoriteEntity(): FavoriteEventEntity {
    return FavoriteEventEntity(
        id = this.id,
        name = this.name,
        imageLogo = this.imageLogo,
        beginTime = this.beginTime
    )
}