package com.achmadichzan.dicodingevents.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.achmadichzan.dicodingevents.presentation.navigation.Route.EventDetail
import com.achmadichzan.dicodingevents.presentation.screen.event_detail.EventDetailScreen
import com.achmadichzan.dicodingevents.presentation.screen.event_list.EventListScreen
import com.achmadichzan.dicodingevents.presentation.screen.EventViewModel
import com.achmadichzan.dicodingevents.presentation.util.EventIntent
import com.achmadichzan.dicodingevents.presentation.util.EventState

@Composable
fun StateListScreen(
    viewModel: EventViewModel,
    navController: NavController,
    eventId: Int? = 9217
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(EventIntent.LoadAllEvents)
    }

    LaunchedEffect(eventId) {
        eventId?.let {
            viewModel.handleIntent(EventIntent.LoadEventDetail(it))
        }
    }

    when (state) {
        is EventState.Loading -> {
            Surface {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        is EventState.Success -> {
            EventListScreen(
                events = (state as EventState.Success).events,
                onEventClick = { eventId ->
                    navController.navigate(EventDetail(eventId))
                }
            )
        }
        is EventState.Error -> {
            Surface {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error: ${(state as EventState.Error).message}")
                }
            }
        }

        is EventState.SuccessDetail -> {
            EventDetailScreen(event = (state as EventState.SuccessDetail).event)
        }
    }
}