package com.croumy.nbascores.presentation.ui.components

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
import com.croumy.nbascores.R
import com.croumy.nbascores.presentation.helpers.TIME
import com.croumy.nbascores.presentation.helpers.asString
import com.croumy.nbascores.presentation.models.enums.GameStatus
import com.croumy.nbascores.presentation.theme.Dimensions

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
                    text = game.period.toString(),
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