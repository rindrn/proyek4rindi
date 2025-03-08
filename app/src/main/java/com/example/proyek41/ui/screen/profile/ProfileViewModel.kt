package com.example.proyek41.ui.screen.profile
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyek41.data.repository.ProfileRepository
import com.example.proyek41.data.local.entity.ProfileEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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
