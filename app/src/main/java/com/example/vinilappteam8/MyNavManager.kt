package com.example.vinilappteam8

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vinilappteam8.views.WelcomeView

@Composable
fun MyNavManager(
    navController: NavHostController,
    sharedViewModel: MainViewModel = hiltViewModel()
) {

    val currentState by sharedViewModel.currentState.collectAsState()

    //val isInitialScreenVisible = currentState.value.isInitialScreenVisible
    //val currentTitle = currentState.value.currentTitle
    //val albumList = sharedViewModel.albums.collectAsState().value

    NavHost(navController = navController, startDestination = "Welcome") {
        composable("Welcome") {
            WelcomeView {
                sharedViewModel.updateTitle("Welcome")
                //sharedViewModel.updateAlbums(listOf("Album 000", "Album 111", "Album 222"))
                navController.navigate("Fake") {
                    popUpTo("Fake") { inclusive = true }
                }
            }

        }
        composable("Fake") {
            FakeWelcomeWindow(
                title = currentState.currentTitle,
                albumList = emptyList(),
                onNavigate = {title ->
                    sharedViewModel.updateTitle(title)
                    //sharedViewModel.updateAlbums(listOf("Album 4", "Album 5", "Album 9999"))
                }
            )
        }
    }

}

@Composable
fun FakeWelcomeWindow(title: String, albumList:List<String> = emptyList(), onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Welcome to VinilApp Team 8")
        Button(onClick = { onNavigate("Other") }) {
            Text(text = "Go to Albums")
        }
        Text(text = "Current Title is: $title", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Albums: ${albumList.joinToString(", ")}", style = MaterialTheme.typography.bodyLarge)
    }


}