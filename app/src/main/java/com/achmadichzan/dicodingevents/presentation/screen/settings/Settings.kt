package com.achmadichzan.dicodingevents.presentation.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(viewModel: SettingsViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Settings")
                }
            )
        }
    ) { innerPadding ->
        val isDarkTheme by viewModel.isDarkThemeEnabled.collectAsStateWithLifecycle()

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
        ) {
            ListItem(
                modifier = Modifier.fillMaxWidth(),
                headlineContent = {
                    Text(
                        text = "Dark theme"
                    )
                },
                supportingContent = {
                    Text("Enable dark theme")
                },
                trailingContent = {
                    Switch(
                        checked = isDarkTheme,
                        onCheckedChange = { isChecked ->
                            viewModel.saveThemeSettings(isChecked)
                        }
                    )
                }
            )
        }
    }
}