package com.achmadichzan.dicodingevents.presentation.screen.event_list.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun EventHeadline() {
    Text(
        text = "Dicoding Events",
        style = MaterialTheme.typography.headlineMedium
    )

    Text(
        text = "Rekomendasi event untukmu",
        style = MaterialTheme.typography.titleMedium
    )
}