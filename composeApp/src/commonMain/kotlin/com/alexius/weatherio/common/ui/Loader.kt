package com.alexius.weatherio.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.alexius.weatherio.common.extension.modifier.modifyIf

@Composable
fun Loader(
    modifier: Modifier = Modifier,
    isSemiTransparent: Boolean = false,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Box(
        modifier = modifier
            .modifyIf(isSemiTransparent) {
                background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
            }
            .pointerInput(Unit) {
                detectDragGestures { _, _ -> }
                detectTapGestures { }
            },
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center),
            color = color,
            strokeWidth = 4.dp,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}