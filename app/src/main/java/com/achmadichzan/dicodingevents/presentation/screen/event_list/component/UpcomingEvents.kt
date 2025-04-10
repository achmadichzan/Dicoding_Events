package com.achmadichzan.dicodingevents.presentation.screen.event_list.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.achmadichzan.dicodingevents.domain.model.Event

@Composable
fun UpcomingEvents(events: List<Event>, onEventClick: (Int) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(
            items = events,
            key = { it.id }
        ) { event ->
            RowEventItem(event = event, onEventClick = onEventClick)
        }
    }
}