package com.example.proyek41.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyek41.ui.navigation.Screen
import com.example.proyek41.ui.components.BottomNavigationBar
import com.example.proyek41.ui.screen.data.DataListScreen
import com.example.proyek41.ui.screen.data.DataTabScreen
import com.example.proyek41.ui.viewmodel.DataViewModel
import com.example.proyek41.ui.screen.profile.ProfileScreen
import com.example.proyek41.ui.screen.detail.DetailScreen
import com.example.proyek41.ui.screen.home.HomeScreen

//import com.example.proyek41.ui.DataEntryScreen
//import com.example.proyek41.ui.EditScreen

@Composable
fun AppNavHost(viewModel: DataViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            if (currentRoute !in listOf("detail/{itemId}/{rewardId}", "edit/{itemId}/{dataId}")) {
                BottomNavigationBar(navController)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.DataEntry.route) }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Data"
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("dataTab") {
                DataTabScreen (navController = navController, viewModel = viewModel) }

            composable(Screen.Home.route) {
                HomeScreen (navController = navController )
            }
            composable(Screen.Data.route) {
                DataListScreen(navController = navController, viewModel = viewModel)
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = "detail/{itemId}/{rewardId}",
                arguments = listOf(
                    navArgument("itemId") { type = NavType.LongType },
                    navArgument("rewardId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val itemId = backStackEntry.arguments?.getLong("itemId") ?: 0L
                val rewardId = backStackEntry.arguments?.getInt("rewardId")?.toLong() ?: 0L

                DetailScreen(
                    itemId = itemId,
                    rewardId = rewardId,
                    navigateBack = { navController.popBackStack() },
                    navigateToCart = { navController.navigate(Screen.Data.route) }
                )
            }
            composable(Screen.DataEntry.route) {
                DataEntryScreen(navController = navController, viewModel = viewModel)
            }
            composable(
                route = "edit/{itemId}/{dataId}",
                arguments = listOf(
                    navArgument("itemId") { type = NavType.LongType },
                    navArgument("dataId") { type = NavType.LongType }
                )
            ) { backStackEntry ->
                val itemId = backStackEntry.arguments?.getLong("itemId") ?: 0L
                val dataId = backStackEntry.arguments?.getLong("dataId") ?: 0L
                EditScreen(
                    itemId = itemId,
                    dataId = dataId,
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}
