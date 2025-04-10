package com.achmadichzan.dicodingevents.presentation.screen.settings.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

data class SettingsItem(
    val title: String,
//    val icon: ImageVector,
    val onClick: () -> Unit
)

val settingsItems = listOf(
    SettingsItem(
        title = "Dark theme",
        onClick = {}
    )
)

@Composable
fun ListItemSettings(item: SettingsItem) {
    ListItem(
        modifier = Modifier.fillMaxWidth(),
        headlineContent = {
            Text(
                text = item.title
            )
        },
        supportingContent = {
            Text("Enable dark theme")
        },
        trailingContent = {
            Switch(
                checked = false,
                onCheckedChange = {

                }
            )
        }
    )
}