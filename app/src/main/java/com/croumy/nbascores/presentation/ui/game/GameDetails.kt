package com.croumy.nbascores.presentation.ui.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.croumy.nbascores.presentation.helpers.asString
import com.croumy.nbascores.presentation.theme.Dimensions
import com.croumy.nbascores.presentation.theme.NBAscoresTheme
import com.croumy.nbascores.presentation.ui.components.StatusItem
import com.croumy.nbascores.presentation.ui.components.TeamItem
import com.croumy.nbascores.presentation.ui.game.components.BigScore
import java.util.Calendar

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun GameDetailsScreen(
    viewModel: GameDetailsViewModel = hiltViewModel(),
    gameId: String
) {
    val currentView = LocalView.current
    val context = LocalContext.current

    val game = viewModel.game.value

    val swipeableState = rememberSwipeableState(0)
    val anchors = mapOf(0f to 0, 1f to 1)


    DisposableEffect(Unit) {
        currentView.keepScreenOn = true
        onDispose { currentView.keepScreenOn = false }
    }

    Scaffold(timeText = { TimeText() }) {
        if (game != null) {
            Box(
                Modifier
                    .fillMaxSize()
                    .swipeable(
                        state = swipeableState,
                        anchors = anchors,
                        reverseDirection = true,
                        thresholds = { _, _ -> FractionalThreshold(0.3f) },
                        orientation = Orientation.Vertical
                    )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(Dimensions.mPadding))
                    Text(Calendar.getInstance().time.asString(), style = MaterialTheme.typography.body2)
                    Spacer(Modifier.height(Dimensions.xsPadding))
                }

                if(swipeableState.offset.value == 0f) {
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
                } else {
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
                                    BigScore(
                                        modifier = Modifier.weight(1f),
                                        game = game
                                    )
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