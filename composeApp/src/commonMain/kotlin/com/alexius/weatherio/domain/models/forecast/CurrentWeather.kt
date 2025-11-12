package com.alexius.weatherio.domain.models.forecast

import com.alexius.weatherio.common.utils.WeatherInfoItem

data class CurrentWeather(
    val temperature: Double,
    val time: String,
    val weatherStatus: WeatherInfoItem,
    val windDirection: String,
    val windSpeed: Double,
    val isDay: Boolean
)
