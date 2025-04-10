package com.achmadichzan.dicodingevents.presentation.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun BottomBar(navController: NavController) {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    BottomAppBar {
        navItem.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    when(item.route) {
                        is Route.EventList -> {
                            navController.navigate(Route.EventList)
                        }
                        is Route.Settings -> {
                            navController.navigate(Route.Settings)
                        }
                    }
                },
                label = { Text(item.title) },
                icon = {
                    if (selectedItemIndex == index) {
                        item.selectedIcon
                    } else {
                        item.unselectedIcon
                    }
                }
            )
        }
    }
}