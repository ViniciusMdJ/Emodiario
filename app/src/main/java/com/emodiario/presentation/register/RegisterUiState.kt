package com.emodiario.presentation.register

import com.emodiario.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
class RegisterUiState: UiState() {
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    fun updateName(value: String){
        _name.value = value
    }

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    fun updateEmail(value: String){
        _email.value = value
    }

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber = _phoneNumber.asStateFlow()

    fun updatePhoneNumber(value: String){
        _phoneNumber.value = value
    }

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun updatePassword(value: String){
        _password.value = value
    }

    private val _showPassword = MutableStateFlow(false)
    val showPassword = _showPassword.asStateFlow()

    fun toggleShowPassword() {
        _showPassword.value = !_showPassword.value
    }

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword = _confirmPassword.asStateFlow()

    fun updateConfirmPassword(value: String){
        _confirmPassword.value = value
    }

    private val _showConfirmPassword = MutableStateFlow(false)
    val showConfirmPassword = _showConfirmPassword.asStateFlow()

    fun toggleShowConfirmPassword() {
        _showConfirmPassword.value = !_showConfirmPassword.value
    }
}