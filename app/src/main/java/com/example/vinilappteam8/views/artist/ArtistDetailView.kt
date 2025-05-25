package com.example.vinilappteam8.views.artist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.vinilappteam8.ui.theme.AppTheme
import com.example.vinilappteam8.ui.theme.spot_green
import com.example.vinilappteam8.viewmodels.artist.ArtistDetailViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun ArtistDetailViewPreview() {
    ArtistDetailView(artistId = 100, viewModel = viewModel(), innerPadding = PaddingValues(8.dp), onBackNavigation = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistDetailView(
    artistId: Int,
    viewModel: ArtistDetailViewModel = viewModel(),
    innerPadding: PaddingValues,
    onBackNavigation: () -> Unit = {}
) {

    val currentArtist by viewModel.currentPerformer.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.getPerformerById(artistId)
    }

    val formattedDate = if(currentArtist?.type == "Band") {
        currentArtist?.creationDate?.let {
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            formatter.timeZone = java.util.TimeZone.getTimeZone("UTC")
            formatter.parse(it)?.let { date ->
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
            }
        }
    } else {
        currentArtist?.birthDate?.let {
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

    AppTheme(darkTheme = true, dynamicColor = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(WindowInsets.systemBars.asPaddingValues())
                .verticalScroll(scrollState)
        ) {
            TopAppBar(

                title = { Text(text = currentArtist?.name ?:"", style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(
                        onClick = { onBackNavigation() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
            AsyncImage(
                model = currentArtist?.image ?:"https://placehold.co/100x100/white/black?text=ArtistImage",
                contentDescription = "Artist Cover",
                contentScale = ContentScale.Fit,
                modifier = Modifier.padding(12.dp).fillMaxWidth()
            )
            Text(
                text = "Nombre: ${currentArtist?.name.toString()}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            )
            Text(
                text = "Fecha Nacimiento / Formacion: $formattedDate",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            )
            Text(
                text = ("Descripcion: " + currentArtist?.description),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(12.dp)
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
                Row {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = "Volver",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

        }
    }




}