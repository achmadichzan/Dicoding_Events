package com.achmadichzan.dicodingevents.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)

val navItem = listOf(
    BottomNavigationItem(
        title = "Events",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        route = RouteConstants.EVENT_LIST
    ),
    BottomNavigationItem(
        title = "Pengaturan",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        route = RouteConstants.SETTINGS
    ),
    BottomNavigationItem(
        title = "Favorit",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        route = RouteConstants.FAVORITE
    ),
)

object RouteConstants {
    val EVENT_LIST = Route.EventList::class.qualifiedName.toString()
    val SETTINGS = Route.Settings::class.qualifiedName.toString()
    val FAVORITE = Route.Favorite::class.qualifiedName.toString()
}