package com.example.vinilappteam8.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun ArtistView(id: Int, innerPadding: PaddingValues) {
    Text(
        modifier = Modifier.padding(innerPadding),
        text = "Artist Screen # $id")
}

@Composable
fun CollectionsView(innerPadding: PaddingValues) {
    Text(
        modifier = Modifier.padding(innerPadding),
        text = "Collections Screen")
}