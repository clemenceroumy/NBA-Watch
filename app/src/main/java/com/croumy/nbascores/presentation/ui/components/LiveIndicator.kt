package com.croumy.nbascores.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.croumy.nbascores.presentation.theme.Dimensions
import com.croumy.nbascores.presentation.theme.red

@Composable
fun LiveIndicator() {
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = red.copy(alpha = 0.2f),
        targetValue = red,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = CubicBezierEasing(0.4f, 0.0f, 0.2f, 1f)),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        Modifier
            .size(Dimensions.xxsIcon)
            .background(color, CircleShape)
    )
}