package com.example.proyek41.ui.screen.profile
import androidx.lifecycle.ViewModel // ✅ Tambahkan ini!
import androidx.lifecycle.viewModelScope // ✅ Tambahkan ini!
import com.example.proyek41.data.repository.ProfileRepository // ✅ Tambahkan ini!
import com.example.proyek41.data.local.entity.ProfileEntity // ✅ Tambahkan ini!
import kotlinx.coroutines.flow.MutableStateFlow // ✅ Tambahkan ini!
import kotlinx.coroutines.flow.StateFlow // ✅ Tambahkan ini!
import kotlinx.coroutines.launch // ✅ Tambahkan ini!

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {
    private val _profileState = MutableStateFlow(ProfileEntity(name = "", email = "", profileImagePath = ""))
    val profileState: StateFlow<ProfileEntity> = _profileState

    init {
        viewModelScope.launch {
            repository.profile.collect {
                _profileState.value = it
            }
        }
    }

    fun saveProfile(name: String, email: String, imagePath: String) {
        viewModelScope.launch {
            repository.saveProfile(ProfileEntity(name = name, email = email, profileImagePath = imagePath))
        }
    }
}
