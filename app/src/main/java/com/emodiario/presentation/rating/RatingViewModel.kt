package com.emodiario.presentation.rating

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emodiario.domain.mapper.toRatingRequest
import com.emodiario.domain.model.Rating
import com.emodiario.domain.use_cases.RegisterRatingUseCase
import com.emodiario.presentation.common.toMessageError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor(
    private val registerRatingUseCase: RegisterRatingUseCase
) : ViewModel() {
    val uiState = RatingUiState()

    fun registerRating(registerSuccessful: () -> Unit, activityId: Int) {
        viewModelScope.launch {

            if(!isDateValid()) {
                uiState.setError("Data inválida")
                return@launch
            }
            if(!isRatingValid()) {
                uiState.setError("Selecione como você classifica a atividade")
                return@launch
            }

            try{
                val rating = Rating(
                    id = -1,
                    date = Date(uiState.date.value),
                    rating = uiState.rating.value!!,
                    description = uiState.description.value
                )
                val resgistred = registerRatingUseCase(
                    activityId = activityId,
                    rating = rating.toRatingRequest(activityId)
                )
                Log.i("RatingViewModel", "Rating created: $resgistred")
                viewModelScope.launch(Dispatchers.Main) { registerSuccessful() }
            } catch (e: HttpException) {
                Log.e("RatingViewModel", e.toMessageError())
                uiState.setError(e.toMessageError())
            } catch (e: Exception) {
                Log.e("RatingViewModel", e.message.orEmpty(), e)
                uiState.setError(e.message.orEmpty())
            }
        }
    }
    private fun isDateValid(): Boolean {
        return uiState.date.value <= System.currentTimeMillis()
    }

    private fun isRatingValid(): Boolean {
        return uiState.rating.value != null
    }
}