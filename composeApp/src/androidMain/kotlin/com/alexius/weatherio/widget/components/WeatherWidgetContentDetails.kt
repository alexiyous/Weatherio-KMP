package com.alexius.weatherio.widget.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.ColorFilter
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.height
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.alexius.weatherio.R
import com.alexius.weatherio.presentation.forecast.components.DEGREE_SYMBOL
import com.alexius.weatherio.widget.mapper.toAndroidDrawable
import com.alexius.weatherio.widget.models.CurrentWeatherWidgetData

@Composable
fun WeatherWidgetContentDetails(
    currentWeather: CurrentWeatherWidgetData,
    iconSize: Dp,
    textSize: TextUnit,
    useRowLayout: Boolean,
    showDetails: Boolean
) {
    Image(
        provider = ImageProvider(currentWeather.weatherStatusIcon),
        contentDescription = currentWeather.weatherStatusInfo,
        modifier = GlanceModifier.size(iconSize),
        colorFilter = ColorFilter.tint(ColorProvider(R.color.widget_primary))
    )
    Spacer(modifier = if (useRowLayout) GlanceModifier.width(12.dp) else GlanceModifier.height(8.dp))
    Column(
        horizontalAlignment = if (useRowLayout) Alignment.Horizontal.Start else Alignment.Horizontal.CenterHorizontally
    ) {
        Text(
            text = "${currentWeather.temperature}$DEGREE_SYMBOL",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = textSize,
                color = ColorProvider(R.color.widget_onSurface)
            ),
        )
        if (showDetails) {
            Spacer(modifier = GlanceModifier.height(8.dp))
            Text(
                text = currentWeather.weatherStatusInfo,
                style = TextStyle(fontSize = 20.sp, color = ColorProvider(R.color.widget_onSurfaceVariant)),
            )
            Text(
                text = currentWeather.windInfo,
                style = TextStyle(fontSize = 16.sp, color = ColorProvider(R.color.widget_onSurfaceVariant)),
            )
        }
    }
}