package com.example.vinilappteam8.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vinilappteam8.MainViewModel
import com.example.vinilappteam8.ui.theme.AppTheme
import com.example.vinilappteam8.viewmodels.album.AlbumDetailViewModel
import com.example.vinilappteam8.viewmodels.album.AlbumListViewModel
import com.example.vinilappteam8.views.WelcomeView
import com.example.vinilappteam8.views.album.AlbumDetailView
import com.example.vinilappteam8.views.album.AlbumListView
import com.example.vinilappteam8.views.components.ScaffoldComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavManager(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel(),
){

    val uiState by viewModel.currentState.collectAsState()

    NavHost(navController = navController, startDestination = if(uiState.isInitialScreenVisible) "Welcome" else "Albums") {

        composable("Welcome") {

            WelcomeView(onNavigate = { navController.navigate("Albums") })
        }

        composable("Albums") {

            var albumViewModel = hiltViewModel<AlbumListViewModel>()

            ScaffoldComponent(
                selectedItem = uiState.selectedNavItem,
                currentTitle = uiState.currentTitle,
                onNavigate = {
                    viewModel.updateTitle("VinilApp Team 8 - $it")
                    viewModel.updateSelectedNavItem(it)
                    //navController.navigate(it)
            }) {
                // Content for Albums screen
                AlbumListView(
                    viewModel = albumViewModel,
                    paddingValues = it,
                    onAlbumSelected = { albumId ->
                        // Handle album selection
                        navController.navigate("AlbumDetails/$albumId")
                    }
                )

            }

        }
        composable("AlbumDetails/{albumId}") { backStackEntry ->

            val albumId = backStackEntry.arguments?.getString("albumId")?.toIntOrNull()
            val viewModel = hiltViewModel<AlbumDetailViewModel>()

            if (albumId != null) {

                AlbumDetailView(
                    viewModel = viewModel,
                    innerPadding = PaddingValues(0.dp),
                    albumId = albumId,
                    onBackNavigation = {
                        navController.popBackStack()
                    }
                )
            }

        }
    }
}