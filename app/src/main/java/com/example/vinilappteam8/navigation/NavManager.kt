package com.example.vinilappteam8.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vinilappteam8.views.CollectionsView
import com.example.vinilappteam8.views.ArtistView
import com.example.vinilappteam8.views.AlbumsView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavManager(navController: NavHostController, innerPadding: PaddingValues){

    NavHost(navController = navController, startDestination = "Albums") {

        composable("Albums") { AlbumsView(innerPadding) }
        composable("Collections") { CollectionsView(innerPadding) }
        composable("Artists") { ArtistView(id = -1, innerPadding) }
        /*composable("Artists/{id}",
            arguments = listOf(
                navArgument("id"){ type = NavType.IntType }
            )
        ) {
            val id = it.arguments?.getInt("id") ?: -1
            DetailView(id = id, innerPadding)
        }*/
    }
}