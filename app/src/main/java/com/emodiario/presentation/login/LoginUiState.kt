package com.emodiario.presentation.login

import com.emodiario.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginUiState : UiState() {
    init {
        setContent()
    }

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun updatePassword(input: String) {
        _password.value = input
    }

    val errorMessageEmail = MutableStateFlow("")
    val isErrorMessageEmailVisible = MutableStateFlow(false)

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    val errorMessagePassword = MutableStateFlow("")
    val isErrorMessagePasswordVisible = MutableStateFlow(false)

    private val _showPassword = MutableStateFlow(false)
    val showPassword = _showPassword.asStateFlow()

    fun toggleShowPassword() {
        _showPassword.value = !_showPassword.value
    }

}