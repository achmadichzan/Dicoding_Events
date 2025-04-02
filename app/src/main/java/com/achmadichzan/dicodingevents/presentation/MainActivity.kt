package com.achmadichzan.dicodingevents.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.achmadichzan.dicodingevents.presentation.navigation.NavMain
import com.achmadichzan.dicodingevents.presentation.ui.theme.DicodingEventsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DicodingEventsTheme {
                NavMain()
            }
        }
    }
}