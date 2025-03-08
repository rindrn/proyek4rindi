package com.example.proyek41.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.proyek41.ui.navigation.NavigationItem
import com.example.proyek41.ui.navigation.Screen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem("Home", Icons.Default.Home, Screen.Home),
        NavigationItem("Data", Icons.Default.List, Screen.Data), // Ganti Cart jadi Data
        NavigationItem("Profile", Icons.Default.Person, Screen.Profile)
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = false, // Ubah dengan logic state nanti
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.startDestinationId) // Hindari duplikasi stack
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
