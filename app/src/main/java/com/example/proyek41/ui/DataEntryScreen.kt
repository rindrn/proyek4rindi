package com.example.proyek41.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyek41.ui.viewmodel.DataViewModel

@Composable
fun DataEntryScreen(navController: NavHostController, viewModel: DataViewModel) {
    val context = LocalContext.current
    var kodeProvinsi by remember { mutableStateOf("") }
    var namaProvinsi by remember { mutableStateOf("") }
    var kodeKabupatenKota by remember { mutableStateOf("") }
    var namaKabupatenKota by remember { mutableStateOf("") }
    var total by remember { mutableStateOf("") }
    var satuan by remember { mutableStateOf("") }
    var tahun by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Input Data",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            CustomTextField(value = kodeProvinsi, label = "Kode Provinsi") { kodeProvinsi = it }
            CustomTextField(value = namaProvinsi, label = "Nama Provinsi") { namaProvinsi = it }
            CustomTextField(value = kodeKabupatenKota, label = "Kode Kabupaten/Kota") { kodeKabupatenKota = it }
            CustomTextField(value = namaKabupatenKota, label = "Nama Kabupaten/Kota") { namaKabupatenKota = it }
            CustomTextField(value = total, label = "Total", keyboardType = KeyboardType.Number) { total = it }
            CustomTextField(value = satuan, label = "Satuan") { satuan = it }
            CustomTextField(value = tahun, label = "Tahun", keyboardType = KeyboardType.Number) { tahun = it }

            Button(
                onClick = {
                    viewModel.insertData(kodeProvinsi, namaProvinsi, kodeKabupatenKota, namaKabupatenKota, total, satuan, tahun)
                    Toast.makeText(context, "Data berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                    navController.navigate("home")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Submit Data", color = Color.White)
            }
        }
    }
}

@Composable
fun CustomTextField(value: String, label: String, keyboardType: KeyboardType = KeyboardType.Text, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier.fillMaxWidth()
    )
}
