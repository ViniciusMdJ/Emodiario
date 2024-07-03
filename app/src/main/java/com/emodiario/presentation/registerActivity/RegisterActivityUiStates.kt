package com.emodiario.presentation.registerActivity

import com.emodiario.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date

class RegisterActivityUiStates : UiState() {

    private val _nameActivity = MutableStateFlow("")
    val nameActivity = _nameActivity.asStateFlow()

    fun updateNameActivity(value: String) {
        _nameActivity.value = value
    }

    private val _description = MutableStateFlow<String?>(null)
    val description = _description.asStateFlow()

    fun updateDescription(value: String) {
        if (value.isEmpty()) {
            _description.value = null
        } else {
            _description.value = value
        }
    }

    private val _startDate = MutableStateFlow(Date())
    val starDate = _startDate.asStateFlow()

    fun updateDate(value: Date) {
        _startDate.value = value
    }
}