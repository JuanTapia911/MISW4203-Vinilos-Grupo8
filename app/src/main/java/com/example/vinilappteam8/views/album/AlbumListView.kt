package com.example.vinilappteam8.views.album

import android.graphics.fonts.FontStyle
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.vinilappteam8.ui.theme.*
import com.example.vinilappteam8.viewmodels.album.AlbumListViewModel


@Composable
fun AlbumListView(
    viewModel: AlbumListViewModel = viewModel(),
    paddingValues: PaddingValues,
    onAlbumSelected: (Int) -> Unit
){
    val albums by viewModel.albums.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    /*LaunchedEffect(Unit) {
        viewModel.fetchAlbumsWithPerformers()
    }*/

        if (errorMessage != null) {
            Text(
                modifier = Modifier.padding(paddingValues),
                text = "Error: $errorMessage",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.error
            )

        } else if (isLoading) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){

                CircularProgressIndicator(
                    modifier = Modifier.padding(paddingValues),
                )
            }

        } else {

            if(albums.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(paddingValues),
                        text = "Loading...",
                        style = MaterialTheme.typography.titleLarge,
                        color = spot_white
                    )
                    CircularProgressIndicator(
                        modifier = Modifier.padding(paddingValues),
                    )
                }
            } else {

                LazyColumn(
                    contentPadding = paddingValues,
                ) {
                    items(items = albums, key = { it.album.id }) { album ->

                        Card(
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    //navController.navigate("AlbumDetails/${album.id}")
                                    onAlbumSelected(album.album.id)
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = spot_gray,
                                contentColor = spot_white
                            )
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = album.album.cover?:"https://placehold.co/100x100/white/black?text=AlbumCover",
                                    contentDescription = "Album Cover",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .padding(10.dp)
                                        .clip(CircleShape)
                                )
                                Column {
                                    Text(
                                        modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 5.dp),
                                        text = album.album.name?:"",
                                        style = MaterialTheme.typography.titleLarge,
                                        color = spot_white
                                    )
                                    Text(
                                        modifier = Modifier.padding(bottom = 5.dp),
                                        text = album.album.genre?:"",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = spot_light_gray,
                                        fontWeight = FontWeight.Bold,
                                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                    )
                                    Text(
                                        modifier = Modifier.padding(bottom = 10.dp),
                                        text = album.performers.firstOrNull()?.name?:"",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = spot_light_gray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

}