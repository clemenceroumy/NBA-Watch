package com.croumy.nbascores.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.items
import androidx.wear.compose.material.rememberScalingLazyListState
import coil.compose.AsyncImage
import com.croumy.nbascores.R
import com.croumy.nbascores.presentation.helpers.TIME
import com.croumy.nbascores.presentation.helpers.asString
import com.croumy.nbascores.presentation.models.enums.GameStatus
import com.croumy.nbascores.presentation.theme.Dimensions
import com.croumy.nbascores.presentation.theme.red
import com.croumy.nbascores.presentation.theme.shimmerColor
import com.croumy.nbascores.presentation.ui.components.LiveIndicator
import com.croumy.nbascores.presentation.ui.components.StatusItem
import com.croumy.nbascores.presentation.ui.components.TeamItem
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.launch
import java.util.Calendar

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToGameDetails: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() }

    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        isRefreshing,
        refreshThreshold = 20.dp,
        onRefresh = {
            coroutineScope.launch {
                isRefreshing = true
                viewModel.getGames()
                isRefreshing = false
            }
        }
    )
    val listState = rememberScalingLazyListState(initialCenterItemIndex = 0)

    LaunchedEffect(Unit) { focusRequester.requestFocus() }

    Scaffold(
        positionIndicator = { PositionIndicator(scalingLazyListState = listState) },
        timeText = { TimeText() }
    ) {
        Box(
            Modifier
                .pullRefresh(pullRefreshState)
                .fillMaxSize()
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = Dimensions.mPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(Calendar.getInstance().time.asString(), style = MaterialTheme.typography.body2)
                Spacer(Modifier.height(Dimensions.xsPadding))
                ScalingLazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = Dimensions.xxsPadding)
                        .onRotaryScrollEvent {
                            coroutineScope.launch {
                                listState.scrollBy(it.verticalScrollPixels)
                            }
                            true
                        }
                        .focusRequester(focusRequester)
                        .focusable(),
                    verticalArrangement = Arrangement.spacedBy(Dimensions.xsPadding),
                    contentPadding = PaddingValues(bottom = Dimensions.sPadding),
                ) {
                    if (viewModel.isLoading.value) {
                        items((0..1).toList()) {
                            Box(
                                Modifier
                                    .shimmer()
                                    .background(shimmerColor, CircleShape)
                                    .fillMaxWidth()
                                    .height(Dimensions.lSize)
                            )
                        }
                    } else {
                        items(viewModel.games.value) {
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colors.surface, CircleShape)
                                    .clickable { navigateToGameDetails(it.gameId) }
                                    .padding(horizontal = Dimensions.sPadding)
                                    .padding(bottom = Dimensions.sPadding),
                            ) {
                                Column(
                                    Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Spacer(Modifier.height(Dimensions.xxsPadding))
                                    StatusItem(game = it)
                                    Spacer(Modifier.height(Dimensions.xxsPadding))
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    TeamItem(team = it.homeTeam)
                                    if (it.gameStatusValue == GameStatus.LIVE || it.gameStatusValue == GameStatus.FINISHED) {
                                        Row {
                                            Spacer(Modifier.width(Dimensions.xsPadding))
                                            Text(text = it.homeTeam.score.toString())
                                        }
                                    }
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    TeamItem(team = it.awayTeam)
                                    if (it.gameStatusValue == GameStatus.LIVE || it.gameStatusValue == GameStatus.FINISHED) {
                                        Row {
                                            Spacer(Modifier.width(Dimensions.xsPadding))
                                            Text(text = it.awayTeam.score.toString())
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            PullRefreshIndicator(isRefreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    HomeScreen()
}