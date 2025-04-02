package com.achmadichzan.dicodingevents.presentation.screen.event_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.achmadichzan.dicodingevents.domain.model.ListEventsItem
import com.achmadichzan.dicodingevents.presentation.screen.event_list.component.EventItem

@Composable
fun EventListScreen(events: List<ListEventsItem>, onEventClick: (Int) -> Unit) {
    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = innerPadding
        ) {
            items(events) { event ->
                EventItem(event = event, onEventClick = onEventClick)
            }
        }
    }
}