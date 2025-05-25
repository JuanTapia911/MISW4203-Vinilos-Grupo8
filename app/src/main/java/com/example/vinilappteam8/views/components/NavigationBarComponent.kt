package com.example.vinilappteam8.views.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp


@Composable
fun NavigationBarComponent(selectedItem: String, onSelectedItem:(String) -> Unit = {}) {

    val items = listOf("Albums", "Artists", "Collectors")
    var selectedIndex = items.indexOf(selectedItem)
    val selectedIcons = listOf(Icons.Filled.LibraryMusic, Icons.Filled.Groups, Icons.Filled.CollectionsBookmark)
    val unselectedIcons = listOf(Icons.Outlined.LibraryMusic, Icons.Outlined.Groups, Icons.Outlined.CollectionsBookmark)

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 0.dp,
        contentColor = MaterialTheme.colorScheme.onPrimary,

    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        if (selectedIndex == index) selectedIcons[index] else unselectedIcons[index],
                        contentDescription = item
                    )
                },
                label = { Text(text = item, fontSize = MaterialTheme.typography.titleMedium.fontSize) },
                selected = selectedIndex == index,
                onClick =  { selectedIndex = index; onSelectedItem(item) },
                colors = NavigationBarItemColors(
                    selectedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                    selectedIconColor = MaterialTheme.colorScheme.onSurface,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.surfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.surfaceVariant,
                    disabledIconColor = MaterialTheme.colorScheme.onSurface,
                    disabledTextColor = MaterialTheme.colorScheme.onSurface
                )

            )
        }
    }




}