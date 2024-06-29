package com.emodiario.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.emodiario.R

@Composable
fun EmodiarioTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = lightColorScheme(
        primary = colorResource(id = R.color.primary),
        secondary = colorResource(id = R.color.secondary),
        tertiary = colorResource(id = R.color.tertiary),
        background = colorResource(id = R.color.background),
        surface = colorResource(id = R.color.surface),
        error = colorResource(id = R.color.error)
    )

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}