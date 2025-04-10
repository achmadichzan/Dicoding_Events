package com.achmadichzan.dicodingevents.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.achmadichzan.dicodingevents.presentation.navigation.NavMain
import com.achmadichzan.dicodingevents.presentation.screen.settings.SettingsViewModel
import com.achmadichzan.dicodingevents.presentation.ui.theme.DicodingEventsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            val isDarkTheme by settingsViewModel.isDarkThemeEnabled.collectAsStateWithLifecycle()

            DicodingEventsTheme(darkTheme = isDarkTheme) {
                Surface {
                    NavMain()
                }
            }
        }
    }
}