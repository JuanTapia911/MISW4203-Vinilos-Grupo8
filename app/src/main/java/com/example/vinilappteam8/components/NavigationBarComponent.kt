package com.example.vinilappteam8.components

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

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.vinilappteam8.ui.theme.VinilAppTeam8Theme
import com.example.vinilappteam8.R

@Preview(showBackground = true)
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
            containerColor = colorResource(R.color.spot_black),
            contentColor = colorResource(R.color.white),
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
                        selectedIndicatorColor = colorResource(R.color.spot_green),
                        selectedIconColor = colorResource(R.color.white),
                        selectedTextColor = colorResource(R.color.white),
                        unselectedIconColor = colorResource(R.color.spot_gray),
                        unselectedTextColor = colorResource(R.color.spot_gray),
                        disabledIconColor = colorResource(R.color.spot_gray),
                        disabledTextColor = colorResource(R.color.spot_gray)
                    )

                    )
            }
        }
    }



}