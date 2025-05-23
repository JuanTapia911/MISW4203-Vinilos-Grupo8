package com.example.vinilappteam8.navigation

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vinilappteam8.viewmodels.album.AlbumDetailViewModel
import com.example.vinilappteam8.viewmodels.album.AlbumListViewModel
import com.example.vinilappteam8.views.album.AlbumDetailView
import com.example.vinilappteam8.views.album.AlbumListView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavManager(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onChangeRouteNavigation: (Boolean, Boolean) -> Unit = { _, _ -> },
){

    NavHost(navController = navController, startDestination = "Albums") {

        composable("Albums") {

            //crear el viewmodel
            //si el viewmodel existe, no lo crea
            val viewModel = hiltViewModel<AlbumListViewModel>()

            AlbumListView(
                viewModel = viewModel,
                paddingValues = innerPadding,
                onAlbumSelected = { albumId ->

                    onChangeRouteNavigation(false, false)
                    navController.navigate("albumDetail/$albumId")
                    Log.d("NavManager", "Album selected: $albumId")
                }
            )
        }
        composable("Collections") { CollectionsView(innerPadding) }
        composable("Artists") { ArtistView(innerPadding)}
        /*composable("Artists/{id}",
            arguments = listOf(
                navArgument("id"){ type = NavType.IntType }
            )
        ) {
            val id = it.arguments?.getInt("id") ?: -1
            DetailView(id = id, innerPadding)
        }*/

        /*composable("albums") {
            AlbumsView(innerPadding = PaddingValues(16.dp), navController = navController)
        }*/
        composable("albumDetail/{albumId}") { backStackEntry ->

            val albumId = backStackEntry.arguments?.getString("albumId")?.toInt() ?: 0
            val viewModel = hiltViewModel<AlbumDetailViewModel>()

            AlbumDetailView(
                albumId = albumId,
                viewModel = viewModel,
                innerPadding = innerPadding,
                onBackNavigation = {
                    onChangeRouteNavigation(true, true)
                    //navController.popBackStack()
                }
            )
        }
    }
}
@Composable
fun ArtistView(x0: PaddingValues) {
    Text("ArtistView",modifier = Modifier.padding(x0))

}

@Composable
fun CollectionsView(x0: PaddingValues) {
    Text("CollectionsView",modifier = Modifier.padding(x0))
}