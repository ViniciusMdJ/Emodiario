package com.emodiario.presentation.common

sealed class ScreenState {
    object Loading : ScreenState()
    data class Error(val message: String = "") : ScreenState()
    object Content : ScreenState()
}