package com.example.proyek41.ui.screen.data

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun OpenDataJabarScreen() {
    val coroutineScope = rememberCoroutineScope()
    val openDataList = remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            // TODO: Panggil API Open Data Jabar di sini
            val fetchedData = listOf("Data 1", "Data 2", "Data 3") // Dummy Data
            openDataList.value = fetchedData
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Data dari Open Data Jabar", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(openDataList.value) { dataItem ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Text(text = dataItem, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}
