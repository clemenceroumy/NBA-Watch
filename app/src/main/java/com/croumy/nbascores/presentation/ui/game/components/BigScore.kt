package com.croumy.nbascores.presentation.ui.game.components

import Game
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.wear.compose.material.Text
import com.croumy.nbascores.presentation.theme.Dimensions
import com.croumy.nbascores.presentation.ui.components.TeamLogo

@Composable
fun BigScore(
    modifier: Modifier = Modifier,
    game: Game
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "")
        Spacer(Modifier.height(Dimensions.xsPadding))
        TeamLogo(team = game.homeTeam)
        Spacer(Modifier.height(Dimensions.xsPadding))
        TeamLogo(team = game.awayTeam)
    }
}