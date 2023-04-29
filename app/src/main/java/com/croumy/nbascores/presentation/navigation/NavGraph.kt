package com.croumy.nbascores.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import com.croumy.nbascores.presentation.ui.game.GameDetailsScreen
import com.croumy.nbascores.presentation.ui.home.HomeScreen


@Composable
fun NavGraph(navController: NavHostController) {
    SwipeDismissableNavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ) {
        composable(NavRoutes.Home.route) {
            HomeScreen()
        }
        composable(NavRoutes.GameDetails.route) {
            GameDetailsScreen()
        }
    }
}
