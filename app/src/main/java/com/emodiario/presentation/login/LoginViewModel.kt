package com.emodiario.presentation.login

import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emodiario.domain.model.User
import com.emodiario.domain.use_cases.LoginUseCase
import com.emodiario.presentation.common.toMessageError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    val uiState = LoginUiState()

    init {
        uiState.setContent()
    }

    fun login(
        onLoginSuccessful: (User) -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!isEmailValid()) {
                uiState.errorMessageEmail.value = "Email inválido"
                return@launch
            } else {
                uiState.errorMessageEmail.value = null
            }

            if (!isPasswordValid()) {
                uiState.errorMessagePassword.value = "Senha deve ter no mínimo 6 caracteres"
                return@launch
            } else {
                uiState.errorMessagePassword.value = null
            }

            try {
                uiState.setLoading()
                val user = loginUseCase(uiState.email.value, uiState.password.value)
                Log.i("LoginViewModel", "User: $user")
                viewModelScope.launch(Dispatchers.Main) {
                    onLoginSuccessful(user)
                }
            } catch (e: HttpException) {
                Log.e("LoginViewModel", e.toMessageError(), e)
                if(e.code() == 401) {
                    uiState.setError("Email ou senha inválidos")
                } else {
                    uiState.setError("Erro ao fazer login")
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error: ${e.message}", e)
                uiState.setError(e.message.orEmpty())
            }

        }
    }

    private fun isEmailValid(): Boolean {
        return EMAIL_ADDRESS.matcher(uiState.email.value).matches()
    }

    private fun isPasswordValid(): Boolean {
        return uiState.password.value.length >= 6
    }
}