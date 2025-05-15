package com.example.vinilappteam8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.vinilappteam8.navigation.NavManager
import com.example.vinilappteam8.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            val navController = rememberNavController()
            val viewModel = hiltViewModel<MainViewModel>()

            AppTheme(dynamicColor = false, darkTheme = true) {
                NavManager(navController = navController, viewModel = viewModel)
            }

            /*VinilAppTeam8Theme(darkTheme = true, dinamycColor=false) {

                var showTopAppBar by rememberSaveable { mutableStateOf(true) }
                var showBottomAppBar by rememberSaveable { mutableStateOf(true) }

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
                    currentTitle = "VinilApp Team 8 - $selectedNavItem"

                }

                if (isInitialScreenVisible) {

                    WelcomeView(onNavigate = {
                        selectedNavItem = "Artists"
                        isInitialScreenVisible = false
                    })

                } else {
                    Scaffold(
                        topBar = {
                            AnimatedVisibility(
                                visible = showTopAppBar

                            ){
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
                                    colors = topAppBarColors(
                                        containerColor = spot_black,
                                        titleContentColor = spot_white,
                                        navigationIconContentColor = spot_white
                                    )

                                )
                            }

                        },
                        floatingActionButton = {ActionFloatingButton()},
                        bottomBar = {
                            AnimatedVisibility(
                                visible = showBottomAppBar
                            ) {
                                // Bottom navigation bar
                                NavigationBarComponent(
                                    selectedItem = selectedNavItem,
                                    onSelectedItem = {
                                        selectedNavItem = it
                                        currentTitle = "VinilApp Team 8 - $it"
                                        when (it) {
                                            "Albums" -> navController.navigate("Albums")
                                            "Artists" -> navController.navigate("Artists")
                                            "Collections" -> navController.navigate("Collections")
                                        }
                                    }
                                )
                            }
                        }
                    ) { innerPadding ->

                        NavManager(
                            navController = navController,
                            innerPadding = innerPadding,
                            onChangeRouteNavigation = { _showTopAppBar, _showBottomAppBar ->
                                showTopAppBar = _showTopAppBar
                                showBottomAppBar = _showBottomAppBar
                            }

                        )

                    }
                }
            }*/




        }
    }
}