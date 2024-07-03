package com.emodiario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.emodiario.domain.preferences.Prefs
import com.emodiario.navigation.MainNavHost
import com.emodiario.ui.theme.EmodiarioTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmodiarioTheme {
                MainNavHost(
                    onBackPressed = { this@MainActivity.onBackPressedDispatcher.onBackPressed() },
                    prefs = Prefs(this)
                )
            }
        }
    }
}