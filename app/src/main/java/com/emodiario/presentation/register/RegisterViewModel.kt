package com.emodiario.presentation.register

import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emodiario.Constraints.Companion.PHONE_NUMBER_LENGTH
import com.emodiario.data.remote.dto.UserRequest
import com.emodiario.domain.model.User
import com.emodiario.domain.use_cases.RegisterUseCase
import com.emodiario.presentation.common.toMessageError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    val uiState = RegisterUiState()

    init {
        uiState.setContent()
    }

    fun register(navigatSuccessful: (User) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!isNameValid()) {
                uiState.errorMessageName.value = "Nome inválido"
                uiState.isErrorMessageNameVisible.value = true
                Log.i("RegisterViewModel", "Name invalid: ${uiState.name.value}")
                return@launch
            } else {
                uiState.errorMessageName.value = ""
                uiState.isErrorMessageNameVisible.value = false
            }

            if (!isEmailValid()) {
                uiState.errorMessageEmail.value = "Email inválido"
                uiState.isErrorMessageEmailVisible.value = true
                Log.i("RegisterViewModel", "Email invalid: ${uiState.email.value}")
                return@launch
            } else {
                uiState.isErrorMessageEmailVisible.value = false
                uiState.errorMessageEmail.value = ""
            }
            if (!isPhoneNumberValid()) {
                uiState.errorMessagePhoneNumber.value = "Numero de telefone inválido"
                uiState.isErrorMessagePhoneNumberVisible.value = true
                Log.i("RegisterViewModel", "Phone number invalid: ${uiState.phoneNumber.value}")
                return@launch
            } else {
                uiState.errorMessagePhoneNumber.value = ""
                uiState.isErrorMessagePhoneNumberVisible.value = false
            }
            if (!isPasswordValid()) {
                uiState.errorMessagePassword.value =
                    "Senha deve ter no mínimo 6 caracteres e corresponder a senha de confirmação"
                uiState.errorMessageConfirmPassword.value =
                    "Senha deve ter no mínimo 6 caracteres e corresponder a senha de confirmação"
                uiState.isErrorMessageConfirmPasswordVisible.value = true
                uiState.isErrorMessagePasswordVisible.value = true
                Log.i("RegisterViewModel", "Password invalid")
                return@launch
            } else {
                uiState.errorMessagePassword.value = ""
                uiState.errorMessageConfirmPassword.value = ""
                uiState.isErrorMessageConfirmPasswordVisible.value = false
                uiState.isErrorMessagePasswordVisible.value = false
            }
            try {
                uiState.setLoading()
                val user = registerUseCase(
                    UserRequest(
                        name = uiState.name.value,
                        email = uiState.email.value,
                        phoneNumber = uiState.phoneNumber.value,
                        password = uiState.password.value
                    )
                )
                Log.i("RegisterViewModel", "User registered: $user")
                viewModelScope.launch(Dispatchers.Main) {
                    navigatSuccessful(user)
                }
            } catch (e: HttpException) {
                Log.e("RegisterViewModel", e.toMessageError(), e)
                uiState.setError(e.response()?.errorBody()?.string().orEmpty())
            } catch (e: Exception) {
                Log.e("RegisterViewModel", e.message.orEmpty(), e)
                uiState.setError(e.message.orEmpty())
            }
        }

    }

    private fun isNameValid() = uiState.name.value.length >= 3

    private fun isEmailValid() = EMAIL_ADDRESS.matcher(uiState.email.value).matches()

    private fun isPasswordValid(): Boolean {
        val password = uiState.password.value
        val confirmPassword = uiState.confirmPassword.value
        return password.length >= 6 && password == confirmPassword
    }

    private fun isPhoneNumberValid() = uiState.phoneNumber.value.length == PHONE_NUMBER_LENGTH
}