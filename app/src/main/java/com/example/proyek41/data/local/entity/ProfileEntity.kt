package com.example.proyek41.data.local.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileEntity(
    @PrimaryKey val id: Int = 1, // Hanya satu profil yang disimpan
    val name: String,
    val email: String,
    val profileImagePath: String // Path gambar profil
)
