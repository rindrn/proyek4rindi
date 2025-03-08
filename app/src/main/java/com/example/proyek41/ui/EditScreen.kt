package com.example.proyek41.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyek41.data.local.entity.DataEntity
import com.example.proyek41.ui.viewmodel.DataViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    itemId: Long,
    dataId: Long,
    navController: NavHostController,
    viewModel: DataViewModel,
) {
    val context = LocalContext.current

    var kodeProvinsi by remember { mutableStateOf("") }
    var namaProvinsi by remember { mutableStateOf("") }
    var kodeKabupatenKota by remember { mutableStateOf("") }
    var namaKabupatenKota by remember { mutableStateOf("") }
    var total by remember { mutableStateOf("") }
    var satuan by remember { mutableStateOf("") }
    var tahun by remember { mutableStateOf("") }

    LaunchedEffect(dataId) {
        viewModel.loadSelectedData(dataId)
    }

    val selectedData by viewModel.selectedData.collectAsStateWithLifecycle()

    LaunchedEffect(selectedData) {
        selectedData?.let { data ->
            kodeProvinsi = data.kodeProvinsi
            namaProvinsi = data.namaProvinsi
            kodeKabupatenKota = data.kodeKabupatenKota
            namaKabupatenKota = data.namaKabupatenKota
            total = data.total.toString()
            satuan = data.satuan
            tahun = data.tahun.toString()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Data") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = kodeProvinsi,
                onValueChange = { kodeProvinsi = it },
                label = { Text("Kode Provinsi") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = namaProvinsi,
                onValueChange = { namaProvinsi = it },
                label = { Text("Nama Provinsi") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = kodeKabupatenKota,
                onValueChange = { kodeKabupatenKota = it },
                label = { Text("Kode Kabupaten/Kota") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = namaKabupatenKota,
                onValueChange = { namaKabupatenKota = it },
                label = { Text("Nama Kabupaten/Kota") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = total,
                onValueChange = { total = it },
                label = { Text("Total") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = satuan,
                onValueChange = { satuan = it },
                label = { Text("Satuan") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = tahun,
                onValueChange = { tahun = it },
                label = { Text("Tahun") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    if (kodeProvinsi.isBlank() || namaProvinsi.isBlank() || kodeKabupatenKota.isBlank() ||
                        namaKabupatenKota.isBlank() || total.isBlank() || satuan.isBlank() || tahun.isBlank()
                    ) {
                        Toast.makeText(context, "Harap isi semua data!", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    if (selectedData != null) {
                        val updatedData = DataEntity(
                            id = dataId,
                            kodeProvinsi = kodeProvinsi,
                            namaProvinsi = namaProvinsi,
                            kodeKabupatenKota = kodeKabupatenKota,
                            namaKabupatenKota = namaKabupatenKota,
                            total = total.toDoubleOrNull() ?: 0.0,
                            satuan = satuan,
                            tahun = tahun.toIntOrNull() ?: 0
                        )

                        viewModel.updateData(updatedData)
                        Toast.makeText(context, "Data berhasil diupdate!", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    } else {
                        Toast.makeText(context, "Data tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = selectedData != null,
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = "Update Data")
            }
        }
    }
}
