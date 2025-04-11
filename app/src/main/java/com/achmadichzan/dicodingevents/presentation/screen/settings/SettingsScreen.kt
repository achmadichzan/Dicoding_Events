package com.achmadichzan.dicodingevents.presentation.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.achmadichzan.dicodingevents.presentation.screen.settings.component.SettingsListItem
import com.achmadichzan.dicodingevents.presentation.screen.settings.component.settingsListData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
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
        val isReminderEnabled by viewModel.isReminderEnabled.collectAsStateWithLifecycle()
        val context = LocalContext.current

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
        ) {
            settingsListData.forEach { settingItem ->
                val checkedState = when (settingItem.id) {
                    "dark_theme" -> isDarkTheme
                    "reminder" -> isReminderEnabled
                    else -> false
                }
                val checkedChangeLambda: (Boolean) -> Unit = when (settingItem.id) {
                    "dark_theme" -> viewModel::saveThemeSettings

                    "reminder" -> { isChecked ->
                        viewModel.setReminderEnabled(isChecked, context)
                    }

                    else -> { _ -> }
                }

                SettingsListItem(
                    item = settingItem,
                    isChecked = checkedState,
                    onCheckedChange = checkedChangeLambda
                )
            }
        }
    }
}