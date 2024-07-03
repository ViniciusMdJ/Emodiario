package com.emodiario.presentation.common.ui_components

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import com.emodiario.presentation.common.toBrazilianDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDate(
    modifier: Modifier = Modifier,
    date: Long,
    showDatePickerDialog: Boolean,
    setShowDatePickerDialog: (Boolean) -> Unit,
    onConfirmDate: (Long) -> Unit
) {
    val focusManager = LocalFocusManager.current

    val datePickerState = rememberDatePickerState(date)
    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = { setShowDatePickerDialog(false) },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirmDate(datePickerState.selectedDateMillis ?: 0)
                        setShowDatePickerDialog(false)
                    }
                ) {
                    Text(text = "Escolher data")
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }
    TextFieldCustom(
        modifier = modifier
            .onFocusEvent {
                if (it.isFocused) {
                    setShowDatePickerDialog(true)
                    focusManager.clearFocus(force = true)
                }
            },
        text = date.toBrazilianDateFormat(),
        onTextChanged = { },
        placeholderText = "",
        readOnly = true
    )
}

@Preview
@Composable
fun CardDatePreview() {
    CardDate(
        date = System.currentTimeMillis(),
        onConfirmDate = {},
        showDatePickerDialog = false,
        setShowDatePickerDialog = {}
    )
}