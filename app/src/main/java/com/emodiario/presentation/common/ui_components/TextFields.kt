package com.emodiario.presentation.common.ui_components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emodiario.R
import com.emodiario.ui.theme.EmodiarioTheme

@Composable
fun TextFieldCustom(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    placeholderText: String,
    readOnly: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            placeholder = { Text(placeholderText) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = ImeAction.Next
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(id = R.color.background),
                unfocusedContainerColor = colorResource(id = R.color.background),
                disabledContainerColor = colorResource(id = R.color.background),
                focusedBorderColor = colorResource(id = R.color.secondary),
                unfocusedBorderColor = colorResource(id = R.color.secondary),
                errorContainerColor = colorResource(id = R.color.background),
                errorBorderColor = colorResource(id = R.color.error)
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            readOnly = readOnly,
            isError = isError,
            visualTransformation = visualTransformation
        )
        AnimatedVisibility(visible = isError && !errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage!!,
                modifier = Modifier.padding(start = 16.dp),
                color = colorResource(id = R.color.error),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Password,
    text: String,
    onTextChanged: (String) -> Unit,
    showPassword: Boolean,
    toggleVisibility: () -> Unit,
    placeholderText: String = stringResource(id = R.string.password),
    isError: Boolean = false,
    errorMessage: String? = null
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        val focusManager = LocalFocusManager.current
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { toggleVisibility() }) {
                    Icon(
                        painter = painterResource(
                            if (showPassword) R.drawable.ic_visibility_off else R.drawable.ic_visibility
                        ),
                        contentDescription = if (showPassword) "Hide password" else "Show password"
                    )
                }
            },
            placeholder = { Text(placeholderText) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(id = R.color.background),
                unfocusedContainerColor = colorResource(id = R.color.background),
                disabledContainerColor = colorResource(id = R.color.background),
                focusedBorderColor = colorResource(id = R.color.secondary),
                unfocusedBorderColor = colorResource(id = R.color.secondary),
                errorContainerColor = colorResource(id = R.color.background),
                errorBorderColor = colorResource(id = R.color.error)
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = isError
        )
        AnimatedVisibility(visible = (isError && !errorMessage.isNullOrEmpty())) {
            Text(
                text = errorMessage!!,
                modifier = Modifier.padding(start = 16.dp),
                color = colorResource(id = R.color.error),
                fontSize = 12.sp
            )
        }
    }
}

@Preview
@Composable
fun TextFieldCustomPreview() {
    EmodiarioTheme {
        TextFieldCustom(
            modifier = Modifier.padding(16.dp),
            text = "",
            onTextChanged = { },
            placeholderText = "hint",
            isError = true,
            errorMessage = "Error message"
        )
    }
}

@Preview
@Composable
fun PasswordTextFieldPreview() {
    EmodiarioTheme {
        PasswordTextField(
            modifier = Modifier.padding(16.dp),
            text = "",
            onTextChanged = {},
            showPassword = false,
            toggleVisibility = { },
            isError = false,
            errorMessage = "Error message"
        )
    }
}
