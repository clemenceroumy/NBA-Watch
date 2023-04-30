package com.croumy.nbawatch.presentation.ui.components

import Game
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.croumy.nbawatch.R
import com.croumy.nbawatch.presentation.helpers.TIME
import com.croumy.nbawatch.presentation.helpers.asString
import com.croumy.nbawatch.presentation.models.enums.GameStatus
import com.croumy.nbawatch.presentation.theme.Dimensions

@Composable
fun StatusItem(game: Game) {
    when (game.gameStatusValue) {
        GameStatus.NOT_STARTED -> Text(
            text = game.gameTime.asString(TIME),
            style = MaterialTheme.typography.body2,
        )

        GameStatus.LIVE -> {
            Row(verticalAlignment = Alignment.CenterVertically) {
                LiveIndicator()
                Spacer(Modifier.width(Dimensions.xxsPadding))
                Text(
                    text = "Q${game.period}",
                    style = MaterialTheme.typography.body2,
                )
            }
        }

        GameStatus.FINISHED -> Text(
            text = stringResource(id = R.string.done),
            style = MaterialTheme.typography.body2,
        )
    }
}