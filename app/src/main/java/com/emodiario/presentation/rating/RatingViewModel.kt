package com.emodiario.presentation.rating

import androidx.lifecycle.ViewModel
import com.emodiario.domain.model.Activity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor() : ViewModel() {
    val uiState = RatingUiState()

    fun getContent(activityId: String, ratingId: String?) {
        uiState.updateActivity(Activity("1", "Activity 1", "Description 1"))
        if (ratingId != null) {
            // get rating by id
        }
        uiState.setContent()
    }
}