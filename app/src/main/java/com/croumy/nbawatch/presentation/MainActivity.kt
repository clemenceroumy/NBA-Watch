package com.croumy.nbawatch.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.croumy.nbawatch.presentation.navigation.NavGraph
import com.croumy.nbawatch.presentation.theme.nbawatchTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberSwipeDismissableNavController()

            nbawatchTheme {
                NavGraph(navController = navController)
            }
        }
    }
}