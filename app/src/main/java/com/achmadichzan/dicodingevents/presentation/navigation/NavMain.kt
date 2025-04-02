package com.achmadichzan.dicodingevents.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.achmadichzan.dicodingevents.presentation.screen.EventViewModel
import com.achmadichzan.dicodingevents.presentation.screen.StateListScreen

@Composable
fun NavMain() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.EventList
    ) {
        composable<Route.EventList> {
            val viewModel: EventViewModel = hiltViewModel()

            StateListScreen(viewModel, navController)
        }

        composable<Route.EventDetail> {
            val eventId = it.arguments?.getInt("eventId") ?: 9217
            val viewModel: EventViewModel = hiltViewModel()

            println("Event ID: $eventId")

            StateListScreen(viewModel, navController, eventId)
        }
    }
}