package com.emodiario.presentation.common.ui_components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DialogError(
    onDismissRequest: () -> Unit,
    title: String,
    message: String? = null,
    confirmButton: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = confirmButton,
        title = { Text(title) },
        text = { message?.let { Text(it) } }
    )
}