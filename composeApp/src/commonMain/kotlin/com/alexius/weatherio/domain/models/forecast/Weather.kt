package com.alexius.weatherio.domain.models.forecast

data class Weather(
    val currentWeather: CurrentWeather,
    val daily: Daily,
    val hourly: Hourly,
    val timezone: String
)
