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
import com.emodiario.common.ScreenState
import com.emodiario.common.ui_components.LoadingScreen
import com.emodiario.common.ui_components.PasswordTextField
import com.emodiario.common.ui_components.TextFieldCustom
import com.emodiario.ui.theme.EmodiarioTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onRegisterPressed: () -> Unit,
    onForgetPasswordPressed: () -> Unit,
    navigateLoginSuccessful: () -> Unit
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
                    text = viewModel.uiState.email.collectAsState().value,
                    onTextChanged = { viewModel.uiState.updateEmail(it) },
                    keyboardType = KeyboardType.Email,
                    placeholderText = stringResource(id = R.string.email)
                )

                Text(
                    text = stringResource(id = R.string.password),
                    modifier = Modifier.align(Alignment.Start),
                    style = MaterialTheme.typography.bodyLarge
                )
                PasswordTextField(
                    text = viewModel.uiState.password.collectAsState().value,
                    onTextChanged = { viewModel.uiState.updatePassword(it) },
                    showPassword = viewModel.uiState.showPassword.collectAsState().value,
                    toggleVisibility = { viewModel.uiState.toggleShowPassword() }
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
                    onClick = navigateLoginSuccessful
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
        LoginScreen(
            onRegisterPressed = {},
            navigateLoginSuccessful = {},
            onForgetPasswordPressed = {}
        )
    }
}