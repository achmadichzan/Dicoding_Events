package com.achmadichzan.dicodingevents.presentation.screen.favorite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.achmadichzan.dicodingevents.data.local.mapper.toEvent
import com.achmadichzan.dicodingevents.presentation.screen.event_list.component.ColumnEventItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    onEventClick: (Int) -> Unit
) {
    val favoriteEvents by viewModel.favoriteEvents.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadAllFavorites()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favorite Events") }
            )
        }
    ) {  innerPadding ->
        LazyColumn(
            contentPadding = innerPadding
        ) {
            items(
                items = favoriteEvents,
                key = { it.id }
            ) { event ->
                ColumnEventItem(
                    event = event.toEvent(),
                    onEventClick = onEventClick
                )
            }
        }
    }
}