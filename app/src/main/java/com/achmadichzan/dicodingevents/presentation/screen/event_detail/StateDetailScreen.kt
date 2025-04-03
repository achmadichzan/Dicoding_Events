package com.achmadichzan.dicodingevents.presentation.screen.event_detail

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
import com.achmadichzan.dicodingevents.presentation.screen.EventViewModel
import com.achmadichzan.dicodingevents.presentation.util.EventIntent
import com.achmadichzan.dicodingevents.presentation.util.EventState

@Composable
fun StateDetailScreen(
    viewModel: EventViewModel,
    eventId: Int?
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(eventId) {
        eventId?.let {
            viewModel.handleIntent(EventIntent.LoadEventDetail(it))
        }
    }

    when(state){
        EventState.Loading -> {
            Surface {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        is EventState.SuccessDetail -> {
            EventDetailScreen(event = (state as EventState.SuccessDetail).event)
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
        else -> {}
    }
}