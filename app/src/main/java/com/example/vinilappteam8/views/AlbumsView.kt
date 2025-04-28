package com.example.vinilappteam8.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.vinilappteam8.ui.theme.VinilAppTeam8Theme

@Composable
fun AlbumsView(innerPadding: PaddingValues) {
    VinilAppTeam8Theme{
        Text(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(innerPadding).testTag("AlbumsViewTag"),
            text = "Albums View"
        )
    }
}
