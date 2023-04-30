package com.croumy.nbawatch.presentation.ui.game.components

import Game
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.croumy.nbawatch.presentation.theme.Dimensions
import com.croumy.nbawatch.presentation.ui.components.TeamLogo

@Composable
fun QTScores(game: Game) {
    Box(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .height(IntrinsicSize.Max)
                .align(Alignment.Center)
                .padding(top = Dimensions.xsPadding)
                .padding(horizontal = Dimensions.sPadding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            (0..4).map {
                if(it == 0) {
                    Column(
                        Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "")
                        Spacer(Modifier.height(Dimensions.xsPadding))
                        TeamLogo(team = game.homeTeam)
                        Spacer(Modifier.height(Dimensions.xsPadding))
                        TeamLogo(team = game.awayTeam)
                    }
                } else {
                    Column(
                        Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Q$it", style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold))
                        Box(
                            Modifier.size(Dimensions.sIcon),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(game.homeTeam.periods[it - 1].score.toString())
                        }
                        Box(
                            Modifier.size(Dimensions.sIcon),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(game.awayTeam.periods[it - 1].score.toString())
                        }
                    }
                }
            }
        }

        Text(
            text = game.gameScore,
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = -Dimensions.sPadding)
        )
    }
}