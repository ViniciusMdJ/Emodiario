package com.emodiario.presentation.rating

import com.emodiario.presentation.common.UiState
import com.emodiario.domain.model.CommuteRating
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date

class RatingUiState : UiState() {
    private val _rating = MutableStateFlow<CommuteRating?>(null)
    val rating = _rating.asStateFlow()

    fun updateRating(value: CommuteRating?) {
        _rating.value = value
    }

    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    fun updateDescription(value: String) {
        _description.value = value
    }

    private val _date = MutableStateFlow(Date().time)
    val date = _date.asStateFlow()

    fun updateDate(value: Long) {
        _date.value = value
    }

    private val _showDatePickerDialog = MutableStateFlow(false)
    val showDatePickerDialog = _showDatePickerDialog.asStateFlow()

    fun updateShowDatePickerDialog(value: Boolean) {
        _showDatePickerDialog.value = value
    }
}