package com.croumy.nbascores.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.croumy.nbascores.presentation.theme.Dimensions
import com.croumy.nbascores.presentation.theme.orange
import com.croumy.nbascores.presentation.theme.shimmerColor
import com.croumy.nbascores.presentation.theme.surface

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    numberItems: Int,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimensions.xxsPadding)
    ) {
        (0 until numberItems).forEach { index ->
            Box(
                modifier = Modifier
                    .size(Dimensions.xxsIcon)
                    .background(if(index == selectedIndex) orange else surface, CircleShape)
            )
        }
    }
}