package com.achmadichzan.dicodingevents.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.achmadichzan.dicodingevents.presentation.screen.EventViewModel
import com.achmadichzan.dicodingevents.presentation.screen.event_detail.StateDetailScreen
import com.achmadichzan.dicodingevents.presentation.screen.event_list.StateListScreen

@Composable
fun NavMain() {
    val navController = rememberNavController()
    val viewModel: EventViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Route.EventList
    ) {
        composable<Route.EventList> {
            StateListScreen(viewModel, navController)
        }

        composable<Route.EventDetail> {
            val eventId = it.arguments?.getInt("eventId")

            StateDetailScreen(viewModel, eventId)
        }
    }
}