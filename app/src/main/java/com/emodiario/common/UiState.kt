package com.emodiario.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class UiState {

    protected val _screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    open fun setLoading() {
        _screenState.value = ScreenState.Loading
    }

    open fun setContent() {
        _screenState.value = ScreenState.Content
    }

    open fun setError(message: String = "") {
        _screenState.value = ScreenState.Error(message)
    }
}