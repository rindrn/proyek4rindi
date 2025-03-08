package com.example.proyek41.ui.screen.data

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.proyek41.ui.viewmodel.DataViewModel
import com.example.proyek41.data.local.entity.DataEntity
import kotlinx.coroutines.launch

@Composable
fun DataScreen(
    navController: NavHostController,
    viewModel: DataViewModel
) {
    val dataList by viewModel.dataList.observeAsState(initial = emptyList())
    val coroutineScope = rememberCoroutineScope()

    // State untuk dialog konfirmasi hapus
    val showDialog = remember { mutableStateOf(false) }
    val itemToDelete = remember { mutableStateOf<DataEntity?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Data dari Open Data Jabar", fontSize = 24.sp, modifier = Modifier.padding(bottom = 8.dp))
        Text(text = "Menampilkan data dari Open Data Jabar dalam bentuk daftar dan visualisasi.", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(dataList.orEmpty()) { data: DataEntity ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { /* Navigasi ke Detail */ },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = data.namaKabupatenKota ?: "Nama Tidak Tersedia", fontSize = 20.sp)
                        Text(text = "Provinsi: ${data.namaProvinsi ?: "Tidak Ada"}", fontSize = 14.sp)

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            TextButton(onClick = { /* Edit Data */ }) {
                                Text("Edit")
                            }
                            TextButton(onClick = {
                                itemToDelete.value = data
                                showDialog.value = true
                            }) {
                                Text("Hapus")
                            }
                        }
                    }
                }
            }
        }
    }

    // Dialog Konfirmasi Hapus
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Konfirmasi Hapus") },
            text = { Text("Apakah Anda yakin ingin menghapus data ini?") },
            confirmButton = {
                Button(
                    onClick = {
                        itemToDelete.value?.id?.let { id ->
                            coroutineScope.launch {
                                viewModel.deleteData(id)
                                showDialog.value = false
                            }
                        }
                    }
                ) {
                    Text("Hapus")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog.value = false }
                ) {
                    Text("Batal")
                }
            }
        )
    }
}
