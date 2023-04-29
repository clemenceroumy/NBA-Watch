package com.croumy.nbascores.presentation.ui.components

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
import com.croumy.nbascores.presentation.theme.Dimensions

@Composable
fun TeamItem(
    modifier: Modifier = Modifier,
    team: Team,
    reverse: Boolean = false
) {
    val context = LocalContext.current

    CompositionLocalProvider(LocalLayoutDirection provides if(reverse) LayoutDirection.Rtl else LayoutDirection.Ltr) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Image(
                painter = painterResource(
                    id = context.resources.getIdentifier(
                        team.teamTricode.lowercase(),
                        "drawable",
                        context.packageName
                    )
                ),
                contentDescription = "",
                modifier = Modifier.size(Dimensions.sIcon)
            )
            Spacer(Modifier.width(Dimensions.xxsPadding))
            Text(text = team.teamName)
        }
    }
}