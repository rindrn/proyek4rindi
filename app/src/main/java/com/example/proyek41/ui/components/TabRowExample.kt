package com.example.proyek41.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyek41.ui.screen.data.DataListScreen
import com.example.proyek41.ui.viewmodel.DataViewModel
import com.example.proyek41.ui.screen.data.OpenDataJabarScreen

@Composable
fun TabRowExample(navController: NavHostController, viewModel: DataViewModel) {
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
            0 -> DataListScreen(navController, viewModel) // ✅ Pakai viewModel yang diteruskan
            1 -> OpenDataJabarScreen() // ✅ Tidak butuh viewModel
        }
    }
}
