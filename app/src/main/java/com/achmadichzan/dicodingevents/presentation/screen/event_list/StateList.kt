package com.achmadichzan.dicodingevents.presentation.screen.event_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.achmadichzan.dicodingevents.presentation.navigation.Route.EventDetail
import com.achmadichzan.dicodingevents.presentation.screen.EventViewModel
import com.achmadichzan.dicodingevents.presentation.util.EventIntent
import com.achmadichzan.dicodingevents.presentation.util.EventState

@Composable
fun StateList(
    viewModel: EventViewModel,
    navController: NavController,
) {
        Surface {
            val allEventsState by viewModel.state.collectAsStateWithLifecycle()
            val upcomingEventsState by viewModel.upcomingEvents.collectAsStateWithLifecycle()

            var searchQuery by remember { mutableStateOf("") }

            LaunchedEffect(Unit) {
                viewModel.handleIntent(EventIntent.LoadUpcomingEvents)
                viewModel.handleIntent(EventIntent.LoadAllEvents)
            }
            LaunchedEffect(searchQuery) {
                viewModel.handleIntent(EventIntent.SearchEvents(searchQuery))
            }

            val isLoading = allEventsState is EventState.Loading || upcomingEventsState is EventState.Loading
            val errorMessages = listOfNotNull(
                (allEventsState as? EventState.Error)?.message,
                (upcomingEventsState as? EventState.Error)?.message
            )

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                errorMessages.isNotEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Error: ${errorMessages.joinToString()}")
                    }
                }
                allEventsState is EventState.Success && upcomingEventsState is EventState.SuccessUpcoming -> {
                    val allEventsList = (allEventsState as EventState.Success).events
                    val upcomingEventsList = (upcomingEventsState as EventState.SuccessUpcoming).events

                    EventListScreen(
                        upcoming = upcomingEventsList,
                        events = allEventsList,
                        onEventClick = { eventId ->
                            navController.navigate(EventDetail(eventId))
                        },
                        value = searchQuery,
                        onValueChange = { newValue ->
                            searchQuery = newValue
                        }
                    )
                }
                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Waiting for data...")
                    }
                }
            }
        }
}
