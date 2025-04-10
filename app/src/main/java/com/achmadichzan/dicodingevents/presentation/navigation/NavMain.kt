package com.achmadichzan.dicodingevents.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.achmadichzan.dicodingevents.presentation.screen.EventViewModel
import com.achmadichzan.dicodingevents.presentation.screen.event_detail.StateDetail
import com.achmadichzan.dicodingevents.presentation.screen.event_list.StateList
import com.achmadichzan.dicodingevents.presentation.screen.settings.Settings

@Composable
fun NavMain() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) {

        val viewModel: EventViewModel = hiltViewModel()

        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Route.EventList,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
        ) {
            composable<Route.EventList> {
                StateList(viewModel, navController)
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

                StateDetail(
                    viewModel = viewModel,
                    eventId = eventId,
                    onBackClick = {
                        navController.navigateBack()
                    }
                )
            }

            composable<Route.Settings> {
                Settings()
            }
        }
    }
}
