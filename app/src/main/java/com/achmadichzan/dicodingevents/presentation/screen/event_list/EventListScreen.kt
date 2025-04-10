package com.achmadichzan.dicodingevents.presentation.screen.event_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.achmadichzan.dicodingevents.domain.model.Event
import com.achmadichzan.dicodingevents.presentation.screen.event_list.component.AllEvents
import com.achmadichzan.dicodingevents.presentation.screen.event_list.component.EventHeadline
import com.achmadichzan.dicodingevents.presentation.screen.event_list.component.EventSearch

@Composable
fun EventListScreen(
    upcoming: List<Event>,
    events: List<Event>,
    onEventClick: (Int) -> Unit,
    value: String,
    onValueChange: (String) -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding),
        ) {
            EventHeadline()

            EventSearch(
                value = value,
                onValueChange = onValueChange
            )

            AllEvents(
                upcoming = upcoming,
                events = events,
                onEventClick = onEventClick
            )
        }
    }
}