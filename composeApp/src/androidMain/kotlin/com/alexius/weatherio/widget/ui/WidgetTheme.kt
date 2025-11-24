package com.alexius.weatherio.widget.ui

import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.glance.GlanceTheme
import androidx.glance.material3.ColorProviders
import com.alexius.weatherio.R

val WidgetColors = darkColorScheme(
    primary = Color(R.color.widget_primary),
    onPrimary = Color(R.color.widget_onPrimary),
    primaryContainer = Color(R.color.widget_primary),
    onPrimaryContainer = Color(R.color.widget_onPrimary),
    secondary = Color(R.color.widget_secondary),
    onSecondary = Color(R.color.widget_onPrimary),
    secondaryContainer = Color(R.color.widget_secondary),
    onSecondaryContainer = Color(R.color.widget_onPrimary),
    tertiary = Color(R.color.widget_secondary),
    onTertiary = Color(R.color.widget_onPrimary),
    tertiaryContainer = Color(R.color.widget_secondary),
    onTertiaryContainer = Color(R.color.widget_onPrimary),
    error = Color(R.color.widget_primary),
    onError = Color(R.color.widget_onPrimary),
    errorContainer = Color(R.color.widget_primary),
    onErrorContainer = Color(R.color.widget_onPrimary),
    background = Color(R.color.widget_background),
    onBackground = Color(R.color.widget_onBackground),
    surface = Color(R.color.widget_surface),
    onSurface = Color(R.color.widget_onSurface),
    surfaceVariant = Color(R.color.widget_surface),
    onSurfaceVariant = Color(R.color.widget_onSurfaceVariant),
    outline = Color(R.color.widget_onSurfaceVariant),
    outlineVariant = Color(R.color.widget_onSurfaceVariant),
    scrim = Color(R.color.widget_background),
    inverseSurface = Color(R.color.widget_onBackground),
    inverseOnSurface = Color(R.color.widget_background),
    inversePrimary = Color(R.color.widget_primary),
    surfaceTint = Color(R.color.widget_primary),
    surfaceBright = Color(R.color.widget_surface),
    surfaceDim = Color(R.color.widget_background),
    surfaceContainer = Color(R.color.widget_surface),
    surfaceContainerHigh = Color(R.color.widget_surface),
    surfaceContainerHighest = Color(R.color.widget_surface),
    surfaceContainerLow = Color(R.color.widget_surface),
    surfaceContainerLowest = Color(R.color.widget_background),
    primaryFixed = Color(R.color.widget_primary),
    primaryFixedDim = Color(R.color.widget_primary),
    onPrimaryFixed = Color(R.color.widget_onPrimary),
    onPrimaryFixedVariant = Color(R.color.widget_onPrimary),
    secondaryFixed = Color(R.color.widget_secondary),
    secondaryFixedDim = Color(R.color.widget_secondary),
    onSecondaryFixed = Color(R.color.widget_onPrimary),
    onSecondaryFixedVariant = Color(R.color.widget_onPrimary),
    tertiaryFixed = Color(R.color.widget_secondary),
    tertiaryFixedDim = Color(R.color.widget_secondary),
    onTertiaryFixed = Color(R.color.widget_onPrimary),
    onTertiaryFixedVariant = Color(R.color.widget_onPrimary)
)


// Somehow the widget won't respect the values from here, proceed with manual definition in UI for color
@Composable
fun WidgetTheme(content: @Composable () -> Unit) {
    GlanceTheme(
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            ColorProviders(
                light = WidgetColors,
                dark = WidgetColors
            )
        else
            ColorProviders(
                light = WidgetColors,
                dark = WidgetColors
            ),
        content = content
    )
}