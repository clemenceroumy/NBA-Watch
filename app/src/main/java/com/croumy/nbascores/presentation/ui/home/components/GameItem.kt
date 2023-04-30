package com.croumy.nbascores.presentation.ui.home.components

import Game
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.croumy.nbascores.presentation.models.enums.GameStatus
import com.croumy.nbascores.presentation.theme.Dimensions
import com.croumy.nbascores.presentation.ui.components.StatusItem
import com.croumy.nbascores.presentation.ui.components.TeamItem

@Composable
fun GameItem(
    modifier: Modifier = Modifier,
    game: Game,
    navigateToGameDetails: (gameId: String) -> Unit,
) {
    Column(
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface, CircleShape)
            .clip(CircleShape)
            .clickable { navigateToGameDetails(game.gameId) }
            .padding(horizontal = Dimensions.sPadding)
            .padding(bottom = Dimensions.sPadding),
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(Dimensions.xxsPadding))
            StatusItem(game = game)
            Spacer(Modifier.height(Dimensions.xxsPadding))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TeamItem(team = game.homeTeam)
            if (game.gameStatusValue == GameStatus.LIVE || game.gameStatusValue == GameStatus.FINISHED) {
                Row {
                    Spacer(Modifier.width(Dimensions.xsPadding))
                    Text(text = game.homeTeam.score.toString())
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TeamItem(team = game.awayTeam)
            if (game.gameStatusValue == GameStatus.LIVE || game.gameStatusValue == GameStatus.FINISHED) {
                Row {
                    Spacer(Modifier.width(Dimensions.xsPadding))
                    Text(text = game.awayTeam.score.toString())
                }
            }
        }
    }
}