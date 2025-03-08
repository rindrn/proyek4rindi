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
import com.example.proyek41.ui.viewmodel.DataViewModel
import com.example.proyek41.data.local.entity.DataEntity
import kotlinx.coroutines.launch

@Composable
fun DataScreen(
    navController: NavController,
    viewModel: DataViewModel
) {
    val dataList by viewModel.dataList.observeAsState(initial = emptyList()) // ✅ Pakai collectAsState jika pakai StateFlow
    // val dataList by viewModel.dataList.observeAsState(emptyList()) // ✅ Jika pakai LiveData
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Data dari Open Data Jabar", fontSize = 24.sp, modifier = Modifier.padding(bottom = 8.dp))
        Text(text = "Menampilkan data dari Open Data Jabar dalam bentuk daftar dan visualisasi.", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(dataList.orEmpty()) { data: DataEntity -> // ✅ Menghindari null dengan orEmpty()
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
                                data.id?.let { id ->
                                    coroutineScope.launch {
                                        viewModel.deleteData(id) // ✅ Panggil fungsi dari ViewModel
                                    }
                                }
                            }) {
                                Text("Hapus")
                            }
                        }
                    }
                }
            }
        }
    }
}
