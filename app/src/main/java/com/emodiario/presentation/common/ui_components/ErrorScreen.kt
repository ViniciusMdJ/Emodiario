package com.emodiario.presentation.common.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.emodiario.R
import com.emodiario.ui.theme.EmodiarioTheme

@Composable
fun ErrorScreen(
    onRetry: () -> Unit,
    message: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Ocorreu um erro")
        if (message.isNotEmpty()) {
            Text(text = message)
        }
        Button(
            onClick = onRetry
        ) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
 EmodiarioTheme {
     ErrorScreen(
         onRetry = {},
         message = "Erro ao carregar dados"
     )
 }
}

