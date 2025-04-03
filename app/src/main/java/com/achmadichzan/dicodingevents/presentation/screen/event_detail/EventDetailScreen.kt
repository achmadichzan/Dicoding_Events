package com.achmadichzan.dicodingevents.presentation.screen.event_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.achmadichzan.dicodingevents.domain.model.Event
import com.achmadichzan.dicodingevents.presentation.screen.event_detail.component.EventDescription
import com.achmadichzan.dicodingevents.presentation.screen.event_detail.component.EventHeader

@Composable
fun EventDetailScreen(event: Event?) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            EventHeader(event = event)

            EventDescription(event = event)
        }
    }
}