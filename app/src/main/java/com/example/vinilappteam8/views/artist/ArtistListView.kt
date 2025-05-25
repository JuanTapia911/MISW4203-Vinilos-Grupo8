package com.example.vinilappteam8.views.artist

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.vinilappteam8.ui.theme.spot_gray
import com.example.vinilappteam8.ui.theme.spot_light_gray
import com.example.vinilappteam8.ui.theme.spot_white
import com.example.vinilappteam8.viewmodels.artist.ArtistListViewModel
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun ArtistListView(
    viewModel: ArtistListViewModel = viewModel(),
    paddingValues: PaddingValues,
    onArtistSelected: (Int) -> Unit
) {

    val artists by viewModel.performer.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

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

        if (artists.isEmpty()) {
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
                items(items = artists, key = { it.id }) { artist ->


                    val formattedDate = if(artist.type == "Band") {
                        artist.creationDate?.let {
                            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                            formatter.timeZone = java.util.TimeZone.getTimeZone("UTC")
                            formatter.parse(it)?.let { date ->
                                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
                            }
                        }
                    } else {
                        artist.birthDate?.let {
                            val formatter = SimpleDateFormat(
                                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                                Locale.getDefault()
                            )
                            formatter.timeZone = java.util.TimeZone.getTimeZone("UTC")
                            formatter.parse(it)?.let { date ->
                                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
                            }
                        }
                    }

                    Card(
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {

                                onArtistSelected(artist.id)
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
                                model = artist.image
                                    ?: "https://placehold.co/100x100/white/black?text=ArtistFace",
                                contentDescription = "Artist Picture",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(10.dp)
                                    .clip(CircleShape)
                            )
                            Column {

                                Text(
                                    modifier = Modifier.fillMaxWidth()
                                        .padding(top = 10.dp, bottom = 5.dp),
                                    text = (if (artist.type == "Band"){ "Banda ${artist.name}"} else {artist.name}).toString(),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = spot_white
                                )

                                Text(
                                    modifier = Modifier.padding(bottom = 5.dp),
                                    text = formattedDate.toString(),
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = spot_light_gray,
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                )


                            }
                        }
                    }
                }
            }
        }
    }
}