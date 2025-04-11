package com.achmadichzan.dicodingevents.presentation.screen.settings.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class SettingsItemData(
    val id: String,
    val headlineContent: String,
    val supportingContent: String? = null,
    val hasSwitch: Boolean = false
)

val settingsListData = listOf(
    SettingsItemData(
        id = "dark_theme",
        headlineContent = "Tema Gelap",
        supportingContent = "Aktifkan tema gelap",
        hasSwitch = true
    ),
    SettingsItemData(
        id = "reminder",
        headlineContent = "Pengingat",
        supportingContent = "Aktifkan pengingat",
        hasSwitch = true
    )
)

@Composable
fun SettingsListItem(
    modifier: Modifier = Modifier,
    item: SettingsItemData,
    isChecked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?
) {
    ListItem(
        modifier = modifier.fillMaxWidth(),
        headlineContent = { Text(text = item.headlineContent) },
        supportingContent = item.supportingContent?.let { { Text(text = it) } },
        trailingContent =
        if (item.hasSwitch) { {
                Switch(
                    checked = isChecked,
                    onCheckedChange = onCheckedChange
                ) }
        } else { null }
    )
}