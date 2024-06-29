package com.emodiario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.emodiario.navigation.MainNavHost
import com.emodiario.ui.theme.EmodiarioTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            EmodiarioTheme {
                MainNavHost(
                    onBackPressed = { this@MainActivity.onBackPressedDispatcher.onBackPressed() },
                )
            }
        }
    }
}