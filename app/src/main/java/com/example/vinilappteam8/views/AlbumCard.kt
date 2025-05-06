package com.example.vinilappteam8.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.vinilappteam8.models.Album

@Composable
fun AlbumCard(album: Album, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },  // Esto hace la tarjeta clickeable
        shape = MaterialTheme.shapes.medium
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ) {
            // Aquí puedes agregar la imagen del álbum, por ejemplo
            Image(
                painter = rememberAsyncImagePainter(album.cover),
                contentDescription = "Album Cover",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = album.name, style = MaterialTheme.typography.labelMedium)
            Text(text = album.releaseDate, style = MaterialTheme.typography.labelMedium)
        }
    }
}
