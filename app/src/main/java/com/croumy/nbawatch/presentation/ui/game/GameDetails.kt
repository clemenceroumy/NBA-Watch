package com.croumy.nbawatch.presentation.ui.game

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
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
import com.croumy.nbawatch.presentation.helpers.asString
import com.croumy.nbawatch.presentation.theme.Dimensions
import com.croumy.nbawatch.presentation.theme.nbawatchTheme
import com.croumy.nbawatch.presentation.ui.components.PageIndicator
import com.croumy.nbawatch.presentation.ui.components.StatusItem
import com.croumy.nbawatch.presentation.ui.components.TeamItem
import com.croumy.nbawatch.presentation.ui.components.TeamLogo
import com.croumy.nbawatch.presentation.ui.game.components.BigScore
import com.croumy.nbawatch.presentation.ui.game.components.QTScores
import java.util.Calendar

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun GameDetailsScreen(
    viewModel: GameDetailsViewModel = hiltViewModel(),
    gameId: String
) {
    val currentView = LocalView.current

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

               // if(swipeableState.currentValue == 0) {
                    AnimatedVisibility(
                        visible = swipeableState.targetValue == 0,
                        enter = slideInVertically(animationSpec = spring(
                            stiffness = Spring.StiffnessMedium,
                            visibilityThreshold = IntOffset.VisibilityThreshold
                        )) { fullHeight -> -fullHeight },
                        exit = ExitTransition.None
                    ) {
                        BigScore(game = game)
                    }
                //} else {
                    AnimatedVisibility(
                        visible = swipeableState.targetValue == 1,
                        enter = slideInVertically(animationSpec = spring(
                            stiffness = Spring.StiffnessMedium,
                            visibilityThreshold = IntOffset.VisibilityThreshold
                        )) { fullHeight -> fullHeight },
                        exit = ExitTransition.None
                    ) {
                        QTScores(game = game)
                    }
                //}

                PageIndicator(
                    Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = Dimensions.xxsPadding),
                    selectedIndex = swipeableState.targetValue,
                    numberItems = 2
                )
            }
        }
    }
}

@Preview
@Composable
fun GameDetailsPreview() {
    nbawatchTheme {
        GameDetailsScreen(gameId = "1")
    }
}