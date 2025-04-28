package com.example.vinilappteam8.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.vinilappteam8.ui.theme.VinilAppTeam8Theme
import com.example.vinilappteam8.viewmodels.AlbumsViewModel


@Composable
fun AlbumsView(innerPadding: PaddingValues,  navController: NavController) {
    val albumsViewModel: AlbumsViewModel = viewModel()
    val albums by albumsViewModel.albums.observeAsState(emptyList())
    val isNetworkError by albumsViewModel.eventNetworkError.observeAsState(false)

    VinilAppTeam8Theme {
        LazyColumn (modifier = Modifier.padding(innerPadding)) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                // Mostramos un mensaje si hay un error de red
                if (isNetworkError) {
                    Text(text = "Network error. Please try again.", modifier = Modifier.padding(innerPadding))
                }
            }

            items(albums) { album ->
                // Mostrar la tarjeta de cada álbum
                AlbumCard(
                    album = album,
                    onClick = {
                        //selectedAlbum.value = album
                        navController.navigate("albumDetail/${album.albumId}")
                    }
                )
            }
        }
    }
}


