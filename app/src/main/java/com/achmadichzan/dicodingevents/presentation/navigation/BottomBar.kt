package com.achmadichzan.dicodingevents.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.serialization.generateHashCode
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer

@OptIn(InternalSerializationApi::class)
@Composable
fun BottomBar(navController: NavController) {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = when (currentRoute) {
        Route.EventList::class.qualifiedName,
        Route.Settings::class.qualifiedName -> true
        else -> false
    }

    AnimatedVisibility(
        visible = showBottomBar,
        enter = slideInVertically(
            animationSpec = tween(durationMillis = 200)
        ) { fullHeight -> fullHeight },
        exit = slideOutVertically(
            animationSpec = tween(durationMillis = 200)
        ) { fullHeight -> fullHeight }
    ) {
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
}