package com.croumy.nbawatch.presentation.ui.components

import Team
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.BeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.wear.compose.material.Text
import com.croumy.nbawatch.presentation.theme.Dimensions

@Composable
fun TeamItem(
    modifier: Modifier = Modifier,
    team: Team,
    reverse: Boolean = false
) {
    CompositionLocalProvider(LocalLayoutDirection provides if(reverse) LayoutDirection.Rtl else LayoutDirection.Ltr) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            TeamLogo(team = team)
            Spacer(Modifier.width(Dimensions.xxsPadding))
            Text(text = team.teamName)
        }
    }
}