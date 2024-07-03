package com.emodiario.presentation.ratingHistory

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarState
import com.emodiario.presentation.common.UiState
import com.emodiario.domain.model.Rating
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RatingHistoryUiState : UiState() {
    private val _ratingHistory = MutableStateFlow(emptyList<Rating>())

    val ratingHistory = _ratingHistory.asStateFlow()

    fun updateRatingHistory(list: List<Rating>) {
        _ratingHistory.value = list
    }

    @OptIn(ExperimentalMaterial3Api::class)
    val topAppBarState = TopAppBarState(-Float.MAX_VALUE, 0f,0f)

}