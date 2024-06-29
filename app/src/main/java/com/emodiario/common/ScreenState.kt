package com.emodiario.common

sealed class ScreenState {
    object Loading : ScreenState()
    data class Error(val message: String = "") : ScreenState()
    object Content : ScreenState()
}