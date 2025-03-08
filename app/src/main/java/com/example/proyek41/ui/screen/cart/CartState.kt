package com.example.proyek41.ui.screen.cart

import com.example.proyek41.model.OrderReward

data class CartState(
    val orderReward: List<OrderReward>,
    val totalRequiredPoint: Int
)