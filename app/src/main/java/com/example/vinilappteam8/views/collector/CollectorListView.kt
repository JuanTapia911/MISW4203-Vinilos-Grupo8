package com.example.vinilappteam8.views.collector

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.vinilappteam8.models.CachedCollector
import com.example.vinilappteam8.ui.theme.spot_gray
import com.example.vinilappteam8.ui.theme.spot_light_gray
import com.example.vinilappteam8.ui.theme.spot_white

@Preview(showBackground = true)
@Composable
fun CollectorListViewPreview() {
    CollectorListView(
        paddingValues = PaddingValues(8.dp),
        onCollectorSelected = {}
    )
}


@Composable
fun CollectorListView(
    paddingValues: PaddingValues,
    onCollectorSelected: (Int) -> Unit
) {

    val collectors: List<CachedCollector> = listOf(
        CachedCollector(1,"Collector Uno", "1111111111", "email1@example.com"),
        CachedCollector(2,"Collector Dos", "2222222222", "email2@example.com"),
        CachedCollector(3,"Collector Tres", "3333333333", "email3@example.com")
    )

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
                )){

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Person Icon",
                            modifier = Modifier.size(75.dp).padding(10.dp).clip(CircleShape),
                        )
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ){
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
                                color = spot_light_gray,
                                fontWeight = FontWeight.Bold,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                            )
                            Text(
                                modifier = Modifier.padding(bottom = 10.dp),
                                text = collector.telephone?:"",
                                style = MaterialTheme.typography.bodyLarge,
                                color = spot_light_gray
                            )
                        }
                    }
                }

        }
    }

}