package com.croumy.nbascores.presentation

import MainScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.croumy.nbascores.presentation.theme.NBAscoresTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NBAscoresTheme {
                MainScreen()
            }
        }
    }
}