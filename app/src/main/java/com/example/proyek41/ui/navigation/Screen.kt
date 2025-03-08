package com.example.proyek41.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Data : Screen("data")
    data object Profile : Screen("profile")
    data object DataEntry : Screen("form")  // Tambahkan ini
//    data object Edit : Screen("edit/{itemId}/{dataId}")   // Tambahkan ini
    data object Edit : Screen("edit/{itemId}/{dataId}")   // Tambahkan ini

    data object DetailItem : Screen("detail/{itemId}/{rewardId}") {
        fun createRoute(itemId: Long) = "detail/$itemId"
    }
}
