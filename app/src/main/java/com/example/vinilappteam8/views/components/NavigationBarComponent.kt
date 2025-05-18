package com.example.vinilappteam8.views.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.example.vinilappteam8.ui.theme.*


@Composable
fun NavigationBarComponent(selectedItem: String, onSelectedItem:(String) -> Unit = {}) {

    LaunchedEffect(Unit) {
        onSelectedItem("Albums")
    }

    val items = listOf("Albums", "Artists", "Collections")
    var selectedIndex = items.indexOf(selectedItem)
    val selectedIcons = listOf(Icons.Filled.LibraryMusic, Icons.Filled.Groups, Icons.Filled.CollectionsBookmark)
    val unselectedIcons = listOf(Icons.Outlined.LibraryMusic, Icons.Outlined.Groups, Icons.Outlined.CollectionsBookmark)

    VinilAppTeam8Theme(darkTheme = true) {
        NavigationBar(
            containerColor = spot_black,
            contentColor = spot_white,
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            if (selectedIndex == index) selectedIcons[index] else unselectedIcons[index],
                            contentDescription = item
                        )
                    },
                    label = { Text(text = item) },
                    selected = selectedIndex == index,
                    onClick =  { selectedIndex = index; onSelectedItem(item) },
                    colors = NavigationBarItemColors(
                        selectedIndicatorColor = spot_green,
                        selectedIconColor = spot_white,
                        selectedTextColor = spot_white,
                        unselectedIconColor = spot_gray,
                        unselectedTextColor = spot_gray,
                        disabledIconColor = spot_gray,
                        disabledTextColor = spot_gray
                    )

                )
            }
        }
    }



}