package com.croumy.nbascores.presentation.ui.components

import Team
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.croumy.nbascores.presentation.theme.Dimensions

@Composable
fun TeamLogo(team: Team, size: Dp = Dimensions.sIcon) {
    val context = LocalContext.current
    Image(
        painter = painterResource(
            id = context.resources.getIdentifier(
                team.teamTricode.lowercase(),
                "drawable",
                context.packageName
            )
        ),
        contentDescription = "",
        modifier = Modifier.size(size)
    )
}