package com.example.vinilappteam8.views

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vinilappteam8.ui.theme.AppTheme
import com.example.vinilappteam8.ui.theme.spot_gray
import com.example.vinilappteam8.ui.theme.spot_green
import com.example.vinilappteam8.ui.theme.spot_white


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun WelcomeViewPreview() {
    AppTheme(darkTheme = true) {
        WelcomeView {}
    }
}


@Composable
fun WelcomeView(onNavigate: (String) -> Unit) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.height(50.dp),
                onClick = { onNavigate("Collections") },
                colors = ButtonColors(
                    containerColor = spot_green,
                    contentColor = spot_white,
                    disabledContainerColor = MaterialTheme.colorScheme.secondary,
                    disabledContentColor = MaterialTheme.colorScheme.secondary,
            )
            ) {
                Text(
                    text = "Entrar como Invitado",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                )
            }
            Spacer(modifier = Modifier.padding(30.dp))
            Button(
                modifier = Modifier.height(50.dp),
                onClick = {},
                enabled = false,
                colors = ButtonColors(
                    containerColor = spot_gray,
                    contentColor = spot_white,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.onBackground,
                )

            ) {
                Text(
                    text = "Entrar como coleccionista",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                )
            }
        }


}