package com.emodiario.presentation.register

import com.emodiario.Constraints.Companion.PHONE_NUMBER_LENGTH
import com.emodiario.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterUiState: UiState() {
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    fun updateName(value: String){
        _name.value = value
    }

    val errorMessageName = MutableStateFlow("")
    val isErrorMessageNameVisible = MutableStateFlow(false)

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    fun updateEmail(value: String){
        _email.value = value
    }

    val errorMessageEmail = MutableStateFlow("")
    val isErrorMessageEmailVisible = MutableStateFlow(false)

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber = _phoneNumber.asStateFlow()

    fun updatePhoneNumber(value: String){
        if(value.length > PHONE_NUMBER_LENGTH) return
        _phoneNumber.value = value
    }

    val errorMessagePhoneNumber = MutableStateFlow("")
    val isErrorMessagePhoneNumberVisible = MutableStateFlow(false)

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun updatePassword(value: String){
        _password.value = value
    }

    val errorMessagePassword = MutableStateFlow("")
    val isErrorMessagePasswordVisible = MutableStateFlow(false)

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

    val errorMessageConfirmPassword = MutableStateFlow("")
    val isErrorMessageConfirmPasswordVisible = MutableStateFlow(false)

    private val _showConfirmPassword = MutableStateFlow(false)
    val showConfirmPassword = _showConfirmPassword.asStateFlow()

    fun toggleShowConfirmPassword() {
        _showConfirmPassword.value = !_showConfirmPassword.value
    }
}