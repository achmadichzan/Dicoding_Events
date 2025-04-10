package com.achmadichzan.dicodingevents.presentation.screen.event_list.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.achmadichzan.dicodingevents.R
import com.achmadichzan.dicodingevents.domain.model.Event
import com.achmadichzan.dicodingevents.presentation.ui.component.ShimmerEffect

@Composable
fun ColumnEventItem(event: Event, onEventClick: (Int) -> Unit) {
    ListItem(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEventClick(event.id) },
        leadingContent = {
            SubcomposeAsyncImage(
                model = event.imageLogo,
                contentDescription = event.name,
                loading = {
                    ShimmerEffect(modifier = Modifier.size(120.dp))
                },
                error = {
                    Image(
                        painter = painterResource(R.drawable.moai),
                        contentDescription = "Error loading image"
                    )
                },
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
        },
        headlineContent = {
            Text(
                text = event.name ?: "Unknown Event",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
        },
        supportingContent = {
            Text(
                text = event.cityName ?: "Unknown City",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        tonalElevation = 1.dp
    )
}