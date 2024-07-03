package com.emodiario.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emodiario.R
import com.emodiario.domain.model.User
import com.emodiario.presentation.common.PhoneMask
import com.emodiario.presentation.common.ScreenState
import com.emodiario.presentation.common.ui_components.PasswordTextField
import com.emodiario.presentation.common.ui_components.TextFieldCustom
import com.emodiario.ui.theme.EmodiarioTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    navigateRegisterSuccessful: (User) -> Unit
) {
    val screenState = viewModel.uiState.screenState.collectAsState().value
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.register_title),
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable { onBackPressed() },
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Back buttom"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.primary),

                    )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
        ) {
            Text(stringResource(id = R.string.name))

            TextFieldCustom(
                text = viewModel.uiState.name.collectAsState().value,
                onTextChanged = viewModel.uiState::updateName,
                placeholderText = stringResource(id = R.string.full_name),
                isError = viewModel.uiState.isErrorMessageNameVisible.collectAsState().value,
                errorMessage = viewModel.uiState.errorMessageName.collectAsState().value
            )

            Text(stringResource(id = R.string.email))

            TextFieldCustom(
                text = viewModel.uiState.email.collectAsState().value,
                onTextChanged = viewModel.uiState::updateEmail,
                placeholderText = stringResource(id = R.string.email),
                keyboardType = KeyboardType.Email,
                isError = viewModel.uiState.isErrorMessageEmailVisible.collectAsState().value,
                errorMessage = viewModel.uiState.errorMessageEmail.collectAsState().value
            )

            Text(stringResource(id = R.string.phone_number))

            TextFieldCustom(
                text = viewModel.uiState.phoneNumber.collectAsState().value,
                onTextChanged = viewModel.uiState::updatePhoneNumber,
                placeholderText = stringResource(id = R.string.phone_number),
                keyboardType = KeyboardType.Phone,
                isError = viewModel.uiState.isErrorMessagePhoneNumberVisible.collectAsState().value,
                errorMessage = viewModel.uiState.errorMessagePhoneNumber.collectAsState().value,
                visualTransformation = PhoneMask()
            )

            Text(text = stringResource(id = R.string.password))

            PasswordTextField(
                text = viewModel.uiState.password.collectAsState().value,
                onTextChanged = viewModel.uiState::updatePassword,
                showPassword = viewModel.uiState.showPassword.collectAsState().value,
                toggleVisibility = viewModel.uiState::toggleShowPassword,
                isError = viewModel.uiState.isErrorMessagePasswordVisible.collectAsState().value,
                errorMessage = viewModel.uiState.errorMessagePassword.collectAsState().value
            )

            Text(text = stringResource(id = R.string.confirm_password))

            PasswordTextField(
                text = viewModel.uiState.confirmPassword.collectAsState().value,
                onTextChanged = viewModel.uiState::updateConfirmPassword,
                showPassword = viewModel.uiState.showConfirmPassword.collectAsState().value,
                toggleVisibility = viewModel.uiState::toggleShowConfirmPassword,
                isError = viewModel.uiState.isErrorMessageConfirmPasswordVisible.collectAsState().value,
                errorMessage = viewModel.uiState.errorMessageConfirmPassword.collectAsState().value
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                onClick = { viewModel.register(navigateRegisterSuccessful) },
                enabled = screenState != ScreenState.Loading
            ) {
                Text(text = stringResource(id = R.string.btn_register))
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    EmodiarioTheme {
        RegisterScreen(
            navigateRegisterSuccessful = {},
            onBackPressed = {}
        )
    }
}