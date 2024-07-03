package com.emodiario.presentation.ratingHistory

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emodiario.domain.use_cases.GetRatingHistoryUseCase
import com.emodiario.presentation.common.toMessageError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class RatingHistoryViewModel @Inject constructor(
    private val getRatingHistoryUseCase: GetRatingHistoryUseCase
) : ViewModel() {
    val uiState = RatingHistoryUiState()

    fun getRatingHistory(activityId: Int) {
        viewModelScope.launch {
            uiState.setLoading()
            try {
                val ratingHistory = getRatingHistoryUseCase(activityId)
                uiState.updateRatingHistory(ratingHistory)
                uiState.setContent()
            } catch (e: HttpException) {
                uiState.setError(e.toMessageError())
                Log.e("RatingHistoryViewModel", e.toMessageError())
            } catch (e: Exception) {
                uiState.setError(e.message.orEmpty())
                Log.e("RatingHistoryViewModel", e.message.orEmpty(), e)
            }
        }
    }
}