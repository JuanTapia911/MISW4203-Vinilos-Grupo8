package com.example.vinilappteam8.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.vinilappteam8.models.Album
import com.example.vinilappteam8.viewmodels.AlbumDetailViewModel
import com.example.vinilappteam8.viewmodels.AlbumsViewModel

@Composable
fun AlbumDetailView(albumId: Int) {
    // Obtener el ViewModel de la aplicación
    val albumDetailViewModel: AlbumDetailViewModel = viewModel ()

    // Llamamos al método para obtener el álbum por ID
    LaunchedEffect(albumId) {
        albumDetailViewModel.getAlbumById(albumId)
    }

    // Observar los detalles del álbum
    val album by albumDetailViewModel.albumDetail.observeAsState()

    // Verificamos si el álbum es nulo y mostramos una UI apropiada
    album?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            // Mostrar más detalles del álbum o la imagen si es necesario
            Image(painter = rememberAsyncImagePainter(it.cover), contentDescription = "Album cover")
            Text(text = "Album Details", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Name: ${it.name}")
            Text(text = "Release Date: ${it.releaseDate}")
            Text(text = "Description: ${it.description}")

        }
    } ?: run {
        Text(text = "Loading album details...")
    }
}