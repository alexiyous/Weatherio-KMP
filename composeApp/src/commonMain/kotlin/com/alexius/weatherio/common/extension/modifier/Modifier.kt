package com.alexius.weatherio.common.extension.modifier

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.modifyIf(condition: Boolean, block: @Composable Modifier.() -> Modifier): Modifier = if (condition) {
    block(this)
} else {
    this
}