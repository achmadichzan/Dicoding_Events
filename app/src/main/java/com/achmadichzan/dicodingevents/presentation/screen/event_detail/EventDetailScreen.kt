package com.achmadichzan.dicodingevents.presentation.screen.event_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.achmadichzan.dicodingevents.domain.model.ListEventsItem

@Composable
fun EventDetailScreen(event: ListEventsItem) {
    Surface {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = event.mediaCover,
                contentDescription = event.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = event.name ?: "Unknown Event",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = event.description ?: "No Description",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "City: ${event.cityName ?: "Unknown"}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}