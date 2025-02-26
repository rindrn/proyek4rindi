package com.example.proyek41

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyek41.ui.AppNavHost
import com.example.proyek41.ui.theme.Proyek41Theme
import com.example.proyek41.viewmodel.DataViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proyek41Theme {
                // Inisialisasi ViewModel
                val dataViewModel: DataViewModel = viewModel()
                // Menampilkan Navigation Host
                AppNavHost(viewModel = dataViewModel)
            }
        }
    }
}
