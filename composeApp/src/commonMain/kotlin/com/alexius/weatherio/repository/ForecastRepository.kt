package com.alexius.weatherio.repository

import com.alexius.weatherio.domain.models.forecast.Weather

interface ForecastRepository {
    suspend fun fetchWeatherData(
        latitude: Float,
        longitude: Float,
        daily: Array<String>,
        currentWeather: Array<String>,
        hourlyWeather: Array<String>,
        timeFormat: String,
        timeZone: String? = null,
    ): Result<Weather>
}