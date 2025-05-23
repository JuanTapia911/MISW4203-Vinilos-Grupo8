package com.example.vinilappteam8.views.collector

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinilappteam8.ui.theme.VinilAppTeam8Theme
import com.example.vinilappteam8.ui.theme.spot_gray
import com.example.vinilappteam8.ui.theme.spot_white
import com.example.vinilappteam8.viewmodels.collectors.CollectorListViewModel

@Composable
fun CollectorListView( viewModel: CollectorListViewModel = viewModel(),
                         paddingValues: PaddingValues,
                         onCollectorSelected: (Int) -> Unit) {

    val collectors by viewModel.collectors.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    VinilAppTeam8Theme(darkTheme = false, dynamicColor = false) {

        if (errorMessage != null) {
            Text(
                modifier = Modifier.padding(paddingValues),
                text = "Error: $errorMessage",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.error
            )

        } else if (isLoading) {

            CircularProgressIndicator(
                modifier = Modifier.padding(paddingValues),
            )

        } else {

            if(collectors.isEmpty()) {
                Text(
                    modifier = Modifier.padding(paddingValues),
                    text = "No collectors found",
                    style = MaterialTheme.typography.titleLarge,
                    color = spot_white
                )
            } else {

                LazyColumn(
                    contentPadding = paddingValues,
                ) {
                    items(items = collectors, key = { it.id }) { collector ->

                        Card(
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    onCollectorSelected(collector.id)
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
                                Column {
                                    Text(
                                        modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 5.dp),
                                        text = collector.name?:"",
                                        style = MaterialTheme.typography.titleLarge,
                                        color = spot_white
                                    )
                                    Text(
                                        modifier = Modifier.padding(bottom = 5.dp),
                                        text = collector.email?:"",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = spot_gray
                                    )
                                    Text(
                                        modifier = Modifier.padding(bottom = 5.dp),
                                        text = collector.telephone?:"",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = spot_gray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}