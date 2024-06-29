package com.emodiario.presentation.ratingHistory

import androidx.lifecycle.ViewModel
import com.emodiario.domain.model.CommuteRating
import com.emodiario.domain.model.Rating
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class RatingHistoryViewModel @Inject constructor() : ViewModel() {
    val uiState = RatingHistoryUiState()

    fun getRatingHistory(activityId: String) {
        uiState.updateRatingHistory(
            listOf(
                Rating(
                    id = 1,
                    date = Date(System.currentTimeMillis()),
                    rating = CommuteRating.BAD,
                    description = "Teste"
                ),
                Rating(
                    id = 2,
                    date = Date(System.currentTimeMillis()),
                    rating = CommuteRating.GOOD,
                    description = "Teste"
                ),
                Rating(
                    id = 3,
                    date = Date(System.currentTimeMillis()),
                    rating = CommuteRating.EXCELLENT,
                    description = "Teste"
                ),
                Rating(
                    id = 4,
                    date = Date(System.currentTimeMillis()),
                    rating = CommuteRating.AVERAGE,
                    description = "Teste"
                ),
                Rating(
                    id = 5,
                    date = Date(System.currentTimeMillis()),
                    rating = CommuteRating.TERRIBLE,
                    description = "Teste"
                )
            )
        )
    }
}