package com.alexius.weatherio.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = NeuLightPrimary,
    onPrimary = NeuLightOnPrimary,
    background = NeuLightBackground,
    onBackground = NeuLightOnSurface,
    surface = NeuLightSurface,
    onSurface = NeuLightOnSurface,
    surfaceVariant = NeuLightSurface,
    onSurfaceVariant = NeuLightOnSurfaceVariant
)

private val DarkColorScheme = darkColorScheme(
    primary = NeuDarkPrimary,
    onPrimary = NeuDarkOnPrimary,
    background = NeuDarkBackground,
    onBackground = NeuDarkOnSurface,
    surface = NeuDarkSurface,
    onSurface = NeuDarkOnSurface,
    surfaceVariant = NeuDarkSurface,
    onSurfaceVariant = NeuDarkOnSurfaceVariant
)

@Composable
fun WeatherIoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
