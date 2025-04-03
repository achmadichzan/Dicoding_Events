package com.achmadichzan.dicodingevents.presentation.screen.event_detail

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.achmadichzan.dicodingevents.domain.model.Event
import com.achmadichzan.dicodingevents.presentation.screen.event_detail.component.EventDescription
import com.achmadichzan.dicodingevents.presentation.screen.event_detail.component.EventHeader

@Composable
fun EventDetailScreen(event: Event?) {
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.size(width = 100.dp, height = 45.dp),
                onClick = {
                    Intent(Intent.ACTION_VIEW).apply {
                        data = event?.link?.toUri()
                    }.also { context.startActivity(it) }
                }
            ) {
                Text(
                    text = "Lihat Event",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        contentWindowInsets = WindowInsets.safeContent
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            EventHeader(event = event)

            EventDescription(event = event)
        }
    }
}