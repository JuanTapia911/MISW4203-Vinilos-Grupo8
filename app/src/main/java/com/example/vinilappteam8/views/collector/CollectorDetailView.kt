package com.example.vinilappteam8.views.collector

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
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
import com.example.vinilappteam8.ui.theme.VinilAppTeam8Theme
import com.example.vinilappteam8.ui.theme.spot_green
import com.example.vinilappteam8.ui.theme.spot_white
import com.example.vinilappteam8.viewmodels.collectors.CollectorDetailViewModel

@Preview(showBackground = true)
@Composable
fun CollectorDetailViewPreview() {
    CollectorDetailView(collectorId = 1, viewModel = viewModel(), innerPadding = PaddingValues(8.dp), onBackNavigation = {})
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectorDetailView(
    collectorId: Int,
    viewModel: CollectorDetailViewModel = viewModel(),
    innerPadding: PaddingValues,
    onBackNavigation: () -> Unit = {}
) {

    val currentCollector by viewModel.currentCollector.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.getCollectorById(collectorId)
    }

    VinilAppTeam8Theme(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            // Cabecera
            TopAppBar(
                title = { Text(text = currentCollector?.name ?: "", style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = { onBackNavigation() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )

            // Datos personales
            Text(
                text = "Información de contacto",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            Text(text = currentCollector?.email ?: "", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(horizontal = 8.dp))
            Text(text = currentCollector?.telephone ?: "", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))

            // Álbumes
            Text(
                text = "Álbumes",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            currentCollector?.collectorAlbums?.forEach { album ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .heightIn(min = 100.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Álbum ID: ${album.id}")
                        Text("Precio: $${album.price}")
                        Text("Estado: ${album.status}")
                    }
                }
            }

            // Comentarios
            Text(
                text = "Comentarios",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            currentCollector?.comments?.forEach { comment ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .heightIn(min = 100.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Comentario ID: ${comment.id}")
                        Text("Descripción: ${comment.description}")
                        Text("Calificación: ${comment.rating} ⭐")
                    }
                }
            }

            // Artistas favoritos
            Text(
                text = "Artistas favoritos",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            currentCollector?.favoritePerformers?.forEach { performer ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .heightIn(min = 200.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        AsyncImage(
                            model = performer.image,
                            contentDescription = performer.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text("Nombre: ${performer.name}", style = MaterialTheme.typography.titleMedium)
                        Text("Nacimiento: ${performer.birthDate?.take(10)}")
                        Text("Descripción: ${performer.description}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

            // Botón volver
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                onClick = { onBackNavigation() },
                colors = ButtonColors(
                    containerColor = spot_green,
                    contentColor = spot_green,
                    disabledContainerColor = MaterialTheme.colorScheme.secondary,
                    disabledContentColor = MaterialTheme.colorScheme.secondary,
                )
            ) {
                Text(
                    text = "Volver",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = spot_white
                )
            }
        }
    }
}
