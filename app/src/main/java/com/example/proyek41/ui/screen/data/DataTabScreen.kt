package com.example.proyek41.ui.screen.data

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
//import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.proyek41.ui.viewmodel.DataViewModel
import com.example.proyek41.data.local.entity.DataEntity
//import com.example.proyek41.ui.screen.data.DataListScreen
//import com.example.proyek41.ui.screen.data.DataScreen

@Composable
fun DataTabScreen(navController: NavHostController, viewModel: DataViewModel) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Lihat Data", "Kelola Data")

    // Tambahkan MutableState untuk dialog dan item yang akan dihapus
    val showDialog = remember { mutableStateOf(false) }
    val itemToDelete = remember { mutableStateOf<DataEntity?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> DataScreen(navController, viewModel)
            1 -> DataListScreen(
                navController = navController,
                viewModel = viewModel,
                showDialog = showDialog,  // Perbaikan: Tambahkan parameter ini
                itemToDelete = itemToDelete // Perbaikan: Tambahkan parameter ini
            )
        }
    }
}
