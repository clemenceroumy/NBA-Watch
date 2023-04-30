package com.croumy.nbawatch.presentation.navigation

import androidx.navigation.NavHostController

class NavActions(private val navController: NavHostController) {
    fun navigateToGameDetails(gameId: String) {
        navController.navigate(NavRoutes.GameDetails.route.replace("{${NavRoutes.GameDetails.gameId}}", gameId))
    }
}