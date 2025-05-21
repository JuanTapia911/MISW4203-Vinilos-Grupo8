package com.example.vinilappteam8.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vinilappteam8.viewmodels.MainViewModel
import com.example.vinilappteam8.viewmodels.album.AlbumDetailViewModel
import com.example.vinilappteam8.viewmodels.album.AlbumListViewModel
import com.example.vinilappteam8.viewmodels.artist.ArtistListViewModel
import com.example.vinilappteam8.views.WelcomeView
import com.example.vinilappteam8.views.album.AlbumDetailView
import com.example.vinilappteam8.views.album.AlbumListView
import com.example.vinilappteam8.views.artist.ArtistListView
import com.example.vinilappteam8.views.components.ScaffoldComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavManager(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel(),
){

    val uiState by viewModel.currentState.collectAsState()

    val albumViewModel = hiltViewModel<AlbumListViewModel>()
    val albumDetailViewModel = hiltViewModel<AlbumDetailViewModel>()

    val artistViewModel = hiltViewModel<ArtistListViewModel>()


    NavHost(navController = navController, startDestination = "Welcome") {

        composable("Welcome") {

            WelcomeView(
                onNavigate = {
                    viewModel.updateInitialScreen(false)
                    navController.navigate("Albums")
                }
            )
        }

        composable("Artists") {

            viewModel.updateTitle("VinilApp Team 8 - ${it.destination.route}")
            viewModel.updateSelectedNavItem("Artists")

            ScaffoldComponent(
                selectedItem = uiState.selectedNavItem,
                currentTitle = uiState.currentTitle,
                onNavigate = {

                    if(it == "Artists") {
                        return@ScaffoldComponent
                    } else {
                        navController.navigate(it)
                    }

                }) {
                    ArtistListView(
                        viewModel = artistViewModel,
                        paddingValues = it,
                        onArtistSelected = { }
                    )
                }
        }

        composable("Albums") {

            viewModel.updateTitle("VinilApp Team 8 - ${it.destination.route}")
            viewModel.updateSelectedNavItem("Albums")

            ScaffoldComponent(
                selectedItem = uiState.selectedNavItem,
                currentTitle = uiState.currentTitle,
                onNavigate = {

                    if(it == "Albums") {
                        return@ScaffoldComponent
                    } else {
                        navController.navigate(it)
                    }
            }) {
                // Content for Albums screen
                AlbumListView(
                    viewModel = albumViewModel,
                    paddingValues = it,
                    onAlbumSelected = { albumId ->
                        navController.navigate("AlbumDetails/$albumId")
                    }
                )

            }

        }
        composable("AlbumDetails/{albumId}") { backStackEntry ->

            val albumId = backStackEntry.arguments?.getString("albumId")?.toIntOrNull()

            if (albumId != null) {

                AlbumDetailView(
                    viewModel = albumDetailViewModel,
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