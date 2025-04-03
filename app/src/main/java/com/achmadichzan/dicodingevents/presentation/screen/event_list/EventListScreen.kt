package com.achmadichzan.dicodingevents.presentation.screen.event_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.achmadichzan.dicodingevents.domain.model.ListEventsItem
import com.achmadichzan.dicodingevents.presentation.screen.event_list.component.EventHeadline
import com.achmadichzan.dicodingevents.presentation.screen.event_list.component.EventItem
import com.achmadichzan.dicodingevents.presentation.screen.event_list.component.EventSearch

@Composable
fun EventListScreen(
    events: List<ListEventsItem>,
    onEventClick: (Int) -> Unit
) {
    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 10.dp),
            contentPadding = innerPadding,
            horizontalAlignment = Alignment.Start
        ) {

            item { EventHeadline() }

            item { EventSearch() }

            items(
                items = events,
                key = { it.id }
            ) { event ->
                EventItem(event = event, onEventClick = onEventClick)
            }
        }
    }
}