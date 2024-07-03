package com.emodiario.presentation.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emodiario.domain.use_cases.GetProfileUseCase
import com.emodiario.presentation.common.toMessageError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: GetProfileUseCase
) : ViewModel() {
    val uiState = ProfileUiState()

    fun getContent(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                uiState.setLoading()
                val profile = profileRepository(userId)
                uiState.updateName(profile.name)
                uiState.updatePhotoUrl(profile.photoUrl ?: "")
                Log.i("ProfileViewModel", "Profile: $profile")
            } catch (e: HttpException) {
                Log.e("ProfileViewModel", e.toMessageError(), e)
                uiState.setError(e.toMessageError())
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Error: ${e.message}", e)
                uiState.setError(message = e.message.orEmpty())
            }
        }
    }
}