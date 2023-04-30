package com.croumy.nbawatch.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.navArgument
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import com.croumy.nbawatch.presentation.ui.game.GameDetailsScreen
import com.croumy.nbawatch.presentation.ui.home.HomeScreen


@Composable
fun NavGraph(navController: NavHostController) {
    val actions = NavActions(navController = navController)

    SwipeDismissableNavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ) {
        composable(NavRoutes.Home.route) {
            HomeScreen(
                navigateToGameDetails = { actions.navigateToGameDetails(it) }
            )
        }
        composable(
            NavRoutes.GameDetails.route,
            arguments = listOf(navArgument(NavRoutes.GameDetails.gameId) { type = StringType })
        ) {
            GameDetailsScreen(
                gameId = it.arguments?.getString(NavRoutes.GameDetails.gameId) ?: "",
            )
        }
    }
}
