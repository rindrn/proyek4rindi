package com.example.proyek41.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val rewardId: Int,
    val name: String,
    val points: Int,
    val quantity: Int,
    val rewardPoint: Int // Pastikan field ini ada
)
