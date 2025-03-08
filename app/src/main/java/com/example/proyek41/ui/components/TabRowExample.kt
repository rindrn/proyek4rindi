package com.example.proyek41.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TabRowExample() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabTitles = listOf("Kelola Data", "Lihat Data")

    Column {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }
        when (selectedTabIndex) {
            0 -> Text("Data Lokal", modifier = Modifier.padding(16.dp))
            1 -> Text("Data Open Data Jabar", modifier = Modifier.padding(16.dp))
        }
    }
}