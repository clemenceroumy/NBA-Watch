package com.croumy.nbascores.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.croumy.nbascores.presentation.navigation.NavGraph
import com.croumy.nbascores.presentation.theme.NBAscoresTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberSwipeDismissableNavController()

            NBAscoresTheme {
                NavGraph(navController = navController)
            }
        }
    }
}