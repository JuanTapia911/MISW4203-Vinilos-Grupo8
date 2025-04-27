package com.example.vinilappteam8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.rememberNavController
import com.example.vinilappteam8.components.ActionButton
import com.example.vinilappteam8.components.NavigationBarComponent
import com.example.vinilappteam8.navigation.NavManager
import com.example.vinilappteam8.ui.theme.VinilAppTeam8Theme
import com.example.vinilappteam8.views.InitialView

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        setContent {
            VinilAppTeam8Theme(darkTheme = true) {

                var isInitialScreenVisible by remember { mutableStateOf(true) }
                val navController = rememberNavController()
                var currentTitle by remember { mutableStateOf("VinilApp Team 8") }
                var selectedNavItem by remember { mutableStateOf("Albums") }

                navController.addOnDestinationChangedListener { _, destination, _ ->
                    selectedNavItem = when (destination.route) {
                        "Albums" -> "Albums"
                        "Artists" -> "Artists"
                        "Collections" -> "Collections"
                        else -> selectedNavItem
                    }
                    currentTitle = "VinilApp Team 8 $selectedNavItem"
                }

                if(isInitialScreenVisible){
                    InitialView(onNavigate = { isInitialScreenVisible = false })
                } else {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = currentTitle,
                                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                                        color = colorResource(R.color.white)
                                    )
                                },
                                navigationIcon = {
                                    Icon(
                                        imageVector = Icons.Default.LibraryMusic,
                                        contentDescription = "Menu Icon"
                                    )
                                },
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = colorResource(R.color.spot_black),
                                    titleContentColor = colorResource(R.color.white),
                                    navigationIconContentColor = Color.White
                                )
                            )
                        },
                        floatingActionButton = { ActionButton() },
                        bottomBar = {
                            NavigationBarComponent(
                                selectedItem = selectedNavItem,
                                onSelectedItem = {
                                    selectedNavItem = it
                                    currentTitle= "VinilApp Team 8 $it"
                                    when (it) {
                                        "Albums" -> navController.navigate("Albums")
                                        "Artists" -> navController.navigate("Artists")
                                        "Collections" -> navController.navigate("Collections")
                                    }
                            } )

                        }
                    ) { innerPadding -> NavManager(navController = navController, innerPadding = innerPadding)
                        NavManager(navController = navController, innerPadding = innerPadding)
                    }

                }


            }
        }
    }
}