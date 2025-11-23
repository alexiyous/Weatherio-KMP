package com.alexius.weatherio.common.utils.compose

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset

@Composable
fun Modifier.neumorphicUp(
    shape: Shape,
    shadowPadding: Dp,
    color: Color = MaterialTheme.colorScheme.background
) = background(color = color, shape = shape)
    .innerShadow(
        shape = shape,
        shadow = Shadow(
            radius = shadowPadding,
            color = MaterialTheme.colorScheme.surfaceBright,
            offset = DpOffset(x = shadowPadding, y = shadowPadding)
        )
    )
    .innerShadow(
        shape = shape,
        shadow = Shadow(
            radius = shadowPadding,
            color = MaterialTheme.colorScheme.surfaceDim,
            offset = DpOffset(x = -shadowPadding, y = -shadowPadding)
        )
    )

@Composable
fun Modifier.neumorphicDown(
    shape: Shape,
    shadowPadding: Dp,
    color: Color = MaterialTheme.colorScheme.background
) = background(color = color, shape = shape)
    .innerShadow(
        shape = shape,
        shadow = Shadow(
            radius = shadowPadding,
            color = MaterialTheme.colorScheme.surfaceBright,
            offset = DpOffset(x = -shadowPadding, y = -shadowPadding)
        )
    )
    .innerShadow(
        shape = shape,
        shadow = Shadow(
            radius = shadowPadding,
            color = MaterialTheme.colorScheme.surfaceDim,
            offset = DpOffset(x = shadowPadding, y = shadowPadding)
        )
    )