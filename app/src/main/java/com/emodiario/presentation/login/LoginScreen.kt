package com.emodiario.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emodiario.R
import com.emodiario.domain.model.User
import com.emodiario.presentation.common.ScreenState
import com.emodiario.presentation.common.ui_components.CardLoading
import com.emodiario.presentation.common.ui_components.DialogError
import com.emodiario.presentation.common.ui_components.PasswordTextField
import com.emodiario.presentation.common.ui_components.TextFieldCustom
import com.emodiario.ui.theme.EmodiarioTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onRegisterPressed: () -> Unit,
    onForgetPasswordPressed: () -> Unit,
    navigateLoginSuccessful: (User) -> Unit
) {
    val screenState = viewModel.uiState.screenState.collectAsState()
    val email = viewModel.uiState.email.collectAsState()
    val password = viewModel.uiState.password.collectAsState()
    val showPassword = viewModel.uiState.showPassword.collectAsState()
    val errorMessageEmail = viewModel.uiState.errorMessageEmail.collectAsState()
    val errorMessagePassword = viewModel.uiState.errorMessagePassword.collectAsState()

    ScreenContent(
        email = email.value,
        password = password.value,
        showPassword = showPassword.value,
        errorMessageEmail = errorMessageEmail.value,
        errorMessagePassword = errorMessagePassword.value,
        updateEmail = viewModel.uiState::updateEmail,
        updatePassword = viewModel.uiState::updatePassword,
        toggleShowPassword = viewModel.uiState::toggleShowPassword,
        onRegisterPressed = onRegisterPressed,
        onForgetPasswordPressed = onForgetPasswordPressed,
        loginPressed = { viewModel.login(navigateLoginSuccessful) }
    )
    when (screenState.value) {
        ScreenState.Loading -> CardLoading()
        is ScreenState.Error -> {
            DialogError(
                onDismissRequest = { viewModel.uiState.setContent() },
                title = (screenState.value as ScreenState.Error).message,
            ) {
                TextButton(onClick = { viewModel.uiState.setContent() }) {
                    Text(text = stringResource(id = R.string.ok))
                }
            }
        }

        else -> {}
    }
}

@Composable
fun ScreenContent(
    email: String,
    password: String,
    showPassword: Boolean,
    errorMessageEmail: String?,
    errorMessagePassword: String?,
    updateEmail: (String) -> Unit,
    updatePassword: (String) -> Unit,
    toggleShowPassword: () -> Unit,
    onRegisterPressed: () -> Unit,
    onForgetPasswordPressed: () -> Unit,
    loginPressed: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.primary
                    )
                )
            ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 32.dp, horizontal = 24.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(id = R.color.login_background))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 64.dp)
                )

                Text(
                    text = stringResource(id = R.string.email),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.Start)
                )
                TextFieldCustom(
                    text = email,
                    onTextChanged = updateEmail,
                    keyboardType = KeyboardType.Email,
                    placeholderText = stringResource(id = R.string.email),
                    isError = !errorMessageEmail.isNullOrEmpty(),
                    errorMessage = errorMessageEmail
                )

                Text(
                    text = stringResource(id = R.string.password),
                    modifier = Modifier.align(Alignment.Start),
                    style = MaterialTheme.typography.bodyLarge
                )
                PasswordTextField(
                    text = password,
                    onTextChanged = updatePassword,
                    showPassword = showPassword,
                    toggleVisibility = toggleShowPassword,
                    isError = !errorMessagePassword.isNullOrEmpty(),
                    errorMessage = errorMessagePassword
                )

                Text(
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable { onForgetPasswordPressed() },
                    text = stringResource(id = R.string.forgot_password),
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.primary)
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    onClick = loginPressed
                ) {
                    Text(text = stringResource(id = R.string.btn_login))
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.dont_have_account),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        modifier = Modifier.clickable { onRegisterPressed() },
                        text = stringResource(id = R.string.register),
                        color = colorResource(id = R.color.primary),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    EmodiarioTheme {
        ScreenContent(
            email = "",
            password = "aaaaaaaaa",
            showPassword = false,
            errorMessageEmail = null,
            errorMessagePassword = null,
            updateEmail = {},
            updatePassword = {},
            toggleShowPassword = {},
            onRegisterPressed = {},
            onForgetPasswordPressed = {},
            loginPressed = {}
        )
    }
}