package com.emodiario.presentation.registerActivity

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterActivityViewModel @Inject constructor() : ViewModel() {
    val uiState = RegisterActivityUiStates()

    fun registerActivity(){

    }
}