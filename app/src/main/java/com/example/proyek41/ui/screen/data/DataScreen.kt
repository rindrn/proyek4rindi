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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.Alignment




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

    }

    if (dataList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center // Gunakan Alignment.Center di Box
        ) {
            CircularProgressIndicator()
        }
    }


    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(dataList.orEmpty()) { data: DataEntity ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { navController.navigate("detail/${data.id}") },
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = data.namaKabupatenKota ?: "Nama Tidak Tersedia",
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "Provinsi: ${data.namaProvinsi ?: "Tidak Ada"}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = { navController.navigate("edit/${data.id}") }) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit", tint = MaterialTheme.colorScheme.primary)
                        }
                        IconButton(onClick = {
                            itemToDelete.value = data
                            showDialog.value = true
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Hapus", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }
        }
    }

    // Dialog Konfirmasi Hapus
    if (showDialog.value && itemToDelete.value != null) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Konfirmasi Hapus") },
            text = { Text("Apakah Anda yakin ingin menghapus data ini?") },
            confirmButton = {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            itemToDelete.value?.let { viewModel.deleteData(it.id) } // Ubah it menjadi it.id
                        }
                        showDialog.value = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Hapus")
                }
            }
            ,
            dismissButton = {
                Button(onClick = { showDialog.value = false }) { Text("Batal") }
            }
        )
    }
}
