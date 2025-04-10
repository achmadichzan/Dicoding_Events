package com.achmadichzan.dicodingevents.presentation.navigation

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
object Route {

    @Serializable
//    @SerialName("event_list")
    object EventList

    @Serializable
//    @SerialName("event_detail")
    data class EventDetail(val eventId: Int)

    @Serializable
//    @SerialName("settings")
    object Settings
}

val NavHostController.canGoBack: Boolean
    get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

fun NavHostController.navigateBack() {
    if (canGoBack) {
        popBackStack()
    }
}