package com.emodiario.presentation.home

import com.emodiario.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow

class HomeUiState: UiState() {
    val imageUrl = MutableStateFlow<String?>(null)

}