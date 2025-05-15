package com.example.vinilappteam8.views.components

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.vinilappteam8.ui.theme.AppTheme
import com.example.vinilappteam8.ui.theme.spot_black
import com.example.vinilappteam8.ui.theme.spot_white


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ScaffoldComponentPreview() {
    AppTheme(dynamicColor = false, darkTheme = true) {
        ScaffoldComponent("Albums", "VinilApp Team 8") {
            Log.d("Preview", "Selected item: $it")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldComponent(
    selectedItem: String = "Albums",
    currentTitle: String,
    onNavigate: (String) -> Unit = {},
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = currentTitle,
                            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                            color = spot_white
                        )
                    },
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.LibraryMusic,
                            contentDescription = "VinilApp Icon",
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = spot_black,
                        titleContentColor = spot_white,
                        navigationIconContentColor = spot_white
                    )
                )
            },
            floatingActionButton = { ActionFloatingButton() },
            bottomBar = {
                NavigationBarComponent(
                    selectedItem = selectedItem,
                    onSelectedItem = { onNavigate(it) }
                )
            }
        ) { paddingValues ->

            content(paddingValues)
        }

}