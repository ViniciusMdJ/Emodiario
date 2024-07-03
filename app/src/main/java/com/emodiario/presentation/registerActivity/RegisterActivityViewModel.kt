package com.emodiario.presentation.registerActivity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emodiario.data.remote.dto.ActivityRequest
import com.emodiario.data.remote.dto.RatingRequest
import com.emodiario.domain.use_cases.RegisterActivityUseCase
import com.emodiario.presentation.common.toMessageError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class RegisterActivityViewModel @Inject constructor(
    private val registerActivityUseCase: RegisterActivityUseCase
) : ViewModel() {
    val uiState = RegisterActivityUiStates()

    fun registerActivity(userId: Int, onRegisterSuccessful: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!isNameValid()) {
                return@launch
            }

            try {
                uiState.setLoading()
                val activity = registerActivityUseCase(
                    userId = userId,
                    activity = ActivityRequest(
                        name = uiState.nameActivity.value,
                        description = uiState.description.value ?: "",
                    )
                )
                Log.i("RegisterActivityViewModel", "Activity created: $activity")
                onRegisterSuccessful()
            }catch (e: HttpException) {
                Log.e("RegisterActivityViewModel", e.toMessageError(), e)
                uiState.setError(e.toMessageError())
            } catch (e: Exception) {
                Log.e("RegisterActivityViewModel", e.message.orEmpty(), e)
                uiState.setError(e.message.orEmpty())
            }
        }
    }

    private fun isNameValid(): Boolean {
        return uiState.nameActivity.value.isNotEmpty()
    }
}
