package com.croumy.nbascores.presentation.ui.game.components

import Game
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.croumy.nbascores.presentation.theme.Dimensions
import com.croumy.nbascores.presentation.ui.components.StatusItem
import com.croumy.nbascores.presentation.ui.components.TeamItem
import com.croumy.nbascores.presentation.ui.components.TeamLogo

@Composable
fun BigScore(
    modifier: Modifier = Modifier,
    game: Game
) {
    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = Dimensions.xxsPadding)
                .align(Alignment.Center)
        ) {
            TeamItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Dimensions.mPadding),
                team = game.homeTeam
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = game.homeTeam.score.toString(),
                    style = if (game.homeTeam.score > game.awayTeam.score) MaterialTheme.typography.title1 else MaterialTheme.typography.title2
                )
                Spacer(Modifier.width(Dimensions.xsPadding))
                Text(text = "-", style = MaterialTheme.typography.title2)
                Spacer(Modifier.width(Dimensions.xsPadding))
                Text(
                    text = game.awayTeam.score.toString(),
                    style = if (game.awayTeam.score > game.homeTeam.score) MaterialTheme.typography.title1 else MaterialTheme.typography.title2
                )
            }
            TeamItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Dimensions.mPadding),
                team = game.awayTeam,
                reverse = true
            )
        }
        Column(Modifier.align(Alignment.BottomCenter)) {
            StatusItem(game = game)
            Spacer(Modifier.height(Dimensions.sPadding))
        }
    }
}