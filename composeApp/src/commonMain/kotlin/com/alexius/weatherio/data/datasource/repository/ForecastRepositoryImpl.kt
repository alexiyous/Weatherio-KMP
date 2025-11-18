package com.alexius.weatherio.data.datasource.repository

import com.alexius.weatherio.data.datasource.remote.forecast.ForecastRemoteApiService
import com.alexius.weatherio.domain.models.forecast.Weather
import com.alexius.weatherio.repository.ForecastRepository

class ForecastRepositoryImpl(
    private val forecastRemoteApiService: ForecastRemoteApiService
): ForecastRepository {
    override suspend fun fetchWeatherData(
        latitude: Float,
        longitude: Float,
        daily: Array<String>,
        currentWeather: Array<String>,
        hourlyWeather: Array<String>,
        timeFormat: String,
        timeZone: String?
    ): Result<Weather> {
        return runCatching {
            forecastRemoteApiService.fetchForecast(
                latitude = latitude,
                longitude = longitude,
                daily = daily,
                currentWeather = currentWeather,
                hourlyWeather = hourlyWeather,
                timeFormat = timeFormat,
                timeZone = timeZone
            ).getOrThrow()
        }
    }
}