package com.alexius.weatherio.data.datasource.remote.forecast

import com.alexius.weatherio.domain.models.forecast.Weather

interface ForecastRemoteApiService {

    suspend fun fetchForecast(
        latitude: Float,
        longitude: Float,
        daily: Array<String>,
        currentWeather: Array<String>,
        hourlyWeather: Array<String>,
        timeFormat: String,
        timeZone: String? = null,
    ): Result<Weather>
}