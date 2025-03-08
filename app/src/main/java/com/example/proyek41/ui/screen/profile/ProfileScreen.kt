package com.example.proyek41.ui.screen.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProfileScreen() {
    var isEditing by remember { mutableStateOf(false) }
    var studentName by remember { mutableStateOf("Mahasiswa JTK") }
    var studentId by remember { mutableStateOf("22222") }
    var studentEmail by remember { mutableStateOf("mahasiswa@jtk.polban.ac.id") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Avatar Section
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Color.LightGray, CircleShape)
                .clickable { imagePickerLauncher.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
            if (imageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(120.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Default Profile Picture",
                    tint = Color.Gray,
                    modifier = Modifier.size(80.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Profile Info
        Text(text = studentName, style = MaterialTheme.typography.headlineSmall)
        Text(text = "ID: $studentId", style = MaterialTheme.typography.bodyLarge)
        Text(text = studentEmail, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))

        if (isEditing) {
            OutlinedTextField(
                value = studentName,
                onValueChange = { studentName = it },
                label = { Text("Student Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = studentId,
                onValueChange = { studentId = it },
                label = { Text("Student ID") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = studentEmail,
                onValueChange = { studentEmail = it },
                label = { Text("Student Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { isEditing = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
        } else {
            Button(
                onClick = { isEditing = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Edit Profile")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Settings List
        val settingsOptions = listOf("Pengaturan Akun", "Bantuan", "Keluar")
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(settingsOptions) { option ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { /* Handle click */ },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = option, style = MaterialTheme.typography.bodyLarge)
                        Icon(Icons.Filled.ArrowForward, contentDescription = "Go")
                    }
                }
            }
        }
    }
}
