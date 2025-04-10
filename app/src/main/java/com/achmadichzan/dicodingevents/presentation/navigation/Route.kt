package com.achmadichzan.dicodingevents.presentation.navigation

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import kotlinx.serialization.Serializable

@Serializable
object Route {

    @Serializable
    object EventList

    @Serializable
    data class EventDetail(val eventId: Int)

    @Serializable
    object Settings
}

val NavHostController.canGoBack: Boolean
    get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

fun NavHostController.navigateBack() {
    if (canGoBack) {
        popBackStack()
    }
}