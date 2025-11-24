package com.alexius.weatherio.widget.models

import com.alexius.weatherio.common.utils.TextResource
import org.jetbrains.compose.resources.DrawableResource

data class CurrentWeatherWidgetData(
    val temperature: Double,
    val time: String,
    val weatherStatusInfo: String,
    val weatherStatusIcon: DrawableResource,
    val windInfo: String,
    val isDay: Boolean
)
