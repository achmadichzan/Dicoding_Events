package com.achmadichzan.dicodingevents.presentation.screen.event_list.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun RowEventItem(event: Event?, onEventClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .padding(start = 16.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onEventClick(event?.id ?: 0) }
    ) {
        Column(
            modifier = Modifier
                .height(170.dp)
                .width(100.dp)
                .clip(RoundedCornerShape(10.dp)),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Card(
                modifier = Modifier.size(115.dp)
                    .align(Alignment.CenterHorizontally),
            ) {
                SubcomposeAsyncImage(
                    model = event?.imageLogo,
                    contentDescription = event?.name,
                    loading = {
                        ShimmerEffect(modifier = Modifier.size(115.dp))
                    },
                    error = {
                        Image(
                            painter = painterResource(R.drawable.moai),
                            contentDescription = "Error loading image"
                        )
                    },
                    modifier = Modifier
                        .size(115.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = event?.name ?: "Unknown Event",
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            )
        }
    }
}