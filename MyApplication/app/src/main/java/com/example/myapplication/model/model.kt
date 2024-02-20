package com.example.myapplication.model

import androidx.compose.ui.graphics.vector.ImageVector


data class ToggleableInfo(
    val isChecked: Boolean,
    val text: String
)

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

data class TabItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
)
