package com.croumy.nbawatch.presentation.navigation

open class NavRoutes(val route: String) {
    object Home: NavRoutes("Home")
    object GameDetails: NavRoutes("GameDetails/{gameId}") {
        val gameId = "gameId"
    }
}