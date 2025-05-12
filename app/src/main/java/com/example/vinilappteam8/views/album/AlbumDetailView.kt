package com.example.vinilappteam8.views.album

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.vinilappteam8.ui.theme.VinilAppTeam8Theme
import com.example.vinilappteam8.ui.theme.spot_green
import com.example.vinilappteam8.ui.theme.spot_white
import com.example.vinilappteam8.viewmodels.album.AlbumDetailViewModel

@Preview(showBackground = true)
@Composable
fun AlbumDetailViewPreview() {
    AlbumDetailView(albumId = 1, viewModel = viewModel(), innerPadding = PaddingValues(8.dp), onBackNavigation = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailView(
    albumId: Int,
    viewModel: AlbumDetailViewModel = viewModel(),
    innerPadding: PaddingValues,
    onBackNavigation: () -> Unit = {}
) {

    val currentAlbum by viewModel.currentAlbum.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.getAlbumById(albumId)
    }

    VinilAppTeam8Theme(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            TopAppBar(

                title = { Text(text = currentAlbum?.album?.name ?:"", style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(
                        onClick = { onBackNavigation() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
            AsyncImage(
                model = currentAlbum?.album?.cover ?:"https://placehold.co/100x100/white/black?text=AlbumCover",
                contentDescription = "Album Cover",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = currentAlbum?.performers?.joinToString(separator = ","){ it.name.toString()} ?: "No Performers",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
            )
            Text(
                text = currentAlbum?.album?.genre ?: "No Genre",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
            )
            Text(
                text = currentAlbum?.album?.description ?: "No Description",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
            )
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { onBackNavigation() },
                colors = ButtonColors(
                    containerColor = spot_green,
                    contentColor = spot_green,
                    disabledContainerColor = MaterialTheme.colorScheme.secondary,
                    disabledContentColor = MaterialTheme.colorScheme.secondary,
                )
            ){
                Text(
                    text = "Volver",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = spot_white
                )
            }
        }
    }
}
