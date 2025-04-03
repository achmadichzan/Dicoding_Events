package com.achmadichzan.dicodingevents.presentation.screen.event_detail.component

import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.achmadichzan.dicodingevents.domain.model.Event

@Composable
fun EventHeader(
    modifier: Modifier = Modifier,
    event: Event
) {
    ListItem(
        modifier = modifier,
        headlineContent = {
            Text(
                text = event.name ?: "",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        },
        supportingContent = {
            Text(
                text =  "${event.category}, ${event.cityName}" +
                        "\nTerdaftar ${event.registrants.toString()} orang" +
                        "\nMulai ${event.beginTime}" +
                        "\nSelesai ${event.endTime}",
            )
        },
        trailingContent = {
            Text(
                text = "Kuota: ${event.quota.toString()}",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )
        }
    )
}