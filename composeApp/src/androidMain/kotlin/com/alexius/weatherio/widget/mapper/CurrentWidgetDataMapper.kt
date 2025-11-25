package com.alexius.weatherio.widget.mapper

import androidx.glance.appwidget.GlanceAppWidgetManager
import com.alexius.weatherio.common.extension.compose.asString
import com.alexius.weatherio.common.utils.TextResource
import com.alexius.weatherio.domain.models.forecast.CurrentWeather
import com.alexius.weatherio.domain.models.forecast.Daily
import com.alexius.weatherio.widget.WeatherWidget
import com.alexius.weatherio.widget.models.CurrentWeatherWidgetData
import org.jetbrains.compose.resources.getString
import weatherio.composeapp.generated.resources.Res
import weatherio.composeapp.generated.resources.wind_speed

suspend fun CurrentWeather.toWidgetData(daily: Daily): CurrentWeatherWidgetData {
    return CurrentWeatherWidgetData(
        temperature = this.temperature,
        time = this.time,
        weatherStatusInfo = getString(this.weatherStatus.info),
        weatherCode = this.weatherCode,
        windInfo = TextResource.SingleStringResource(Res.string.wind_speed, this.windSpeed, this.windDirection.asString()).asString(),
        isDay = this.isDay,
        uvIndex = daily.dailyWeatherInfo.first().uvIndex,
        usingOldValue = false
    )
}
