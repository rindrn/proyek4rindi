package com.example.proyek41.di

import com.example.proyek41.data.repository.RewardRepository


object Injection {
    fun provideRepository(): RewardRepository {
        return RewardRepository.getInstance()
    }
}