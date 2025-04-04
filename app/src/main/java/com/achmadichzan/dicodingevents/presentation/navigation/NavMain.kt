package com.achmadichzan.dicodingevents.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
        startDestination = Route.EventList,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        composable<Route.EventList> {
            StateListScreen(viewModel, navController)
        }

        composable<Route.EventDetail>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(500)
                )
            }
        ) {
            val eventId = it.arguments?.getInt("eventId")

            StateDetailScreen(viewModel, eventId)
        }
    }
}