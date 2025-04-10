package com.achmadichzan.dicodingevents.presentation.screen.event_detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.achmadichzan.dicodingevents.R
import com.achmadichzan.dicodingevents.domain.model.Event
import com.achmadichzan.dicodingevents.presentation.ui.component.ShimmerEffect

@Composable
fun EventHeader(
    modifier: Modifier = Modifier,
    event: Event?
) {
    SubcomposeAsyncImage(
        model = event?.mediaCover,
        contentDescription = event?.name,
        loading = {
            ShimmerEffect(modifier = Modifier.fillMaxWidth())
        },
        error = {
            Image(
                painter = painterResource(R.drawable.moai),
                contentDescription = "Error loading image"
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        contentScale = ContentScale.FillWidth
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = event?.name ?: "Unknown Event",
        style = MaterialTheme.typography.titleLarge
    )

    Row {
        Text(
            text = "${event?.category} oleh\n${event?.ownerName}",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = ("Kuota ${event?.quota} orang\n" +
                    "Terdaftar ${event?.registrants} orang"),
            style = MaterialTheme.typography.bodySmall,
        )
    }

    Text(
        text = ("Mulai pada ${event?.beginTime}" +
                "\nSelesai pada ${event?.endTime}"),
        style = MaterialTheme.typography.bodySmall
    )

    Text(
        text = "${event?.summary}",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(top = 3.dp)
    )

}