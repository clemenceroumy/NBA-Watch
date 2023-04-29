package com.croumy.nbascores.presentation.ui.game

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import com.croumy.nbascores.presentation.helpers.asString
import com.croumy.nbascores.presentation.theme.Dimensions
import com.croumy.nbascores.presentation.theme.NBAscoresTheme
import com.croumy.nbascores.presentation.ui.components.StatusItem
import com.croumy.nbascores.presentation.ui.components.TeamItem
import java.util.Calendar

@Composable
fun GameDetailsScreen(
    viewModel: GameDetailsViewModel = hiltViewModel(),
    gameId: String
) {
    val game = viewModel.game.value

    Scaffold(timeText = { TimeText() }) {
        if (game != null) {
            Box(Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(Dimensions.mPadding))
                    Text(Calendar.getInstance().time.asString(), style = MaterialTheme.typography.body2)
                    Spacer(Modifier.height(Dimensions.xsPadding))
                }

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
    }
}

@Preview
@Composable
fun GameDetailsPreview() {
    NBAscoresTheme {
        GameDetailsScreen(gameId = "1")
    }
}