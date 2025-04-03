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
fun StateListScreen(
    viewModel: EventViewModel,
    navController: NavController,
) {
    Surface {
        val state by viewModel.state.collectAsStateWithLifecycle()

        var searchQuery by remember { mutableStateOf("") }

        LaunchedEffect(searchQuery) {
            if (searchQuery.isNotEmpty()) {
                viewModel.handleIntent(EventIntent.SearchEvents(searchQuery))
            } else {
                viewModel.handleIntent(EventIntent.LoadAllEvents)
            }
        }

        when (state) {
            is EventState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is EventState.Success -> {
                EventListScreen(
                    events = (state as EventState.Success).events,
                    onEventClick = { eventId ->
                        navController.navigate(EventDetail(eventId))
                    },
                    value = searchQuery,
                    onValueChange = { newValue ->
                        searchQuery = newValue
                        viewModel.handleIntent(EventIntent.SearchEvents(newValue))
                    }
                )
            }
            is EventState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error: ${(state as EventState.Error).message}")
                }
            }
            else -> Unit
        }
    }
}
