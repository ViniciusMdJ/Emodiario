package com.emodiario.presentation.register

import androidx.lifecycle.ViewModel
import com.emodiario.presentation.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    val uiState = RegisterUiState()

}