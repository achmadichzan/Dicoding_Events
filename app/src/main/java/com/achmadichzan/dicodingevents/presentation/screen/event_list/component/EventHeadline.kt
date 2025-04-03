package com.achmadichzan.dicodingevents.presentation.screen.event_list.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EventHeadline() {
    Column (
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    ) {
        Text(
            text = "Dicoding Events",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Rekomendasi event untukmu",
            style = MaterialTheme.typography.titleMedium
        )
    }
}