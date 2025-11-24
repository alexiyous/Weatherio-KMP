package com.alexius.weatherio.widget.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.action.Action
import androidx.glance.action.clickable
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.unit.ColorProvider
import com.alexius.weatherio.R
import com.alexius.weatherio.widget.models.CurrentWeatherWidgetData

@Composable
fun WeatherWidgetLayout(
    useRowLayout: Boolean,
    action: Action,
    currentWeather: CurrentWeatherWidgetData?,
    content: @Composable () -> Unit
) {
    if (useRowLayout) {
        Row(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(ColorProvider(R.color.widget_background))
                .padding(12.dp)
                .clickable(action),
            verticalAlignment = Alignment.Vertical.CenterVertically,
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
        ) {
            if (currentWeather != null) {
                content()
            }
        }
    } else {
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(ColorProvider(R.color.widget_background))
                .padding(12.dp)
                .clickable(action),
            verticalAlignment = Alignment.Vertical.CenterVertically,
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
        ) {
            if (currentWeather != null) {
                content()
            }
        }
    }
}