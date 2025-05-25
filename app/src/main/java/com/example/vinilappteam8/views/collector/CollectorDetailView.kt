package com.example.vinilappteam8.views.collector

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.vinilappteam8.ui.theme.AppTheme

@Preview(showBackground = true)
@Composable
fun CollectorDetailViewPreview() {
    AppTheme(darkTheme = true, dynamicColor = false) {
        CollectorDetailView(collectorId = 1, innerPadding = PaddingValues(8.dp), onBackNavigation = {})
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectorDetailView(
    collectorId: Int = 1, // Default value for preview
    innerPadding: PaddingValues,
    onBackNavigation: () -> Unit = {}
) {

    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Comments", "Artists", "Albums")
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(innerPadding)
            .padding(WindowInsets.systemBars.asPaddingValues())
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "Collector Name",
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Text(
            text = "Collector Phone",
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Text(
            text = "Collector Email",
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        SecondaryTabRow(
            selectedTabIndex = selectedTab,
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(tab) }
                )

            }
        }
        when (selectedTab) {
            0 -> {
                // Comments Tab
                Text(
                    text = "Comments",
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                )
            }
            1 -> {
                // Artists Tab
                Text(
                    text = "Artists",
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                )
            }
            2 -> {
                // Albums Tab
                Text(
                    text = "Albums",
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                )
            }
        }
    }


}