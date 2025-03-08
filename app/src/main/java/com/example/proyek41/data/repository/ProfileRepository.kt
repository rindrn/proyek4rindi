package com.example.proyek41.data.repository
import com.example.proyek41.data.local.dao.ProfileDao
import com.example.proyek41.data.local.entity.ProfileEntity
import kotlinx.coroutines.flow.Flow // Tambahkan ini!

class ProfileRepository(private val profileDao: ProfileDao) {
    val profile: Flow<ProfileEntity> = profileDao.getProfile()

    suspend fun saveProfile(profile: ProfileEntity) {
        profileDao.saveProfile(profile)
    }
}
