package com.croumy.nbawatch.presentation.ui.components

import Team
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.croumy.nbawatch.presentation.theme.Dimensions

@Composable
fun TeamLogo(team: Team, size: Dp = Dimensions.sIcon) {
    val context = LocalContext.current
    val picture = remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(team.teamTricode) {
        val identifier = context.resources.getIdentifier(
            team.teamTricode.lowercase(),
            "drawable",
            context.packageName
        )

        picture.value = if(identifier != 0) identifier else null
    }

    Image(
        painter = painterResource(
            id = picture.value ?: context.resources.getIdentifier(
                "placeholder",
                "drawable",
                context.packageName,
            )
        ),
        contentDescription = "",
        modifier = Modifier.size(size).padding(if(picture.value == null) Dimensions.xxsPadding else 0.dp)
    )
}