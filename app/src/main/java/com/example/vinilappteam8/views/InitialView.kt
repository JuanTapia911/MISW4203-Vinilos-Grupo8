package com.example.vinilappteam8.views

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vinilappteam8.R
import com.example.vinilappteam8.ui.theme.VinilAppTeam8Theme


@Preview(showBackground = true)
@Composable
fun PreviewInitialScreen(){

    InitialView(onNavigate = {})

}

@Composable
fun InitialView(onNavigate: (String) -> Unit){

    VinilAppTeam8Theme(darkTheme = true) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Button(
                modifier = Modifier.height(50.dp),
                onClick = { onNavigate("Home") },
                colors = ButtonColors(
                    containerColor = colorResource(R.color.spot_green),
                    contentColor = colorResource(R.color.white),
                    disabledContainerColor = MaterialTheme.colorScheme.secondary,
                    disabledContentColor = MaterialTheme.colorScheme.secondary,
                )
            ){
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
                    containerColor = colorResource(R.color.spot_gray),
                    contentColor = colorResource(R.color.white),
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
}