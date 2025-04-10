package com.achmadichzan.dicodingevents.presentation.screen.event_list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.achmadichzan.dicodingevents.domain.model.Event

@Composable
fun AllEvents(upcoming: List<Event>, events: List<Event>, onEventClick: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Text(
                text = "Event Mendatang",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            UpcomingEvents(events = upcoming, onEventClick = onEventClick)
        }

        item {
            Text(
                text = "Semua Event",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        items(
            items = events,
            key = { it.id }
        ) { event ->
            ColumnEventItem(event = event, onEventClick = onEventClick)
        }
    }
}