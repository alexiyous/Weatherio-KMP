package com.alexius.weatherio.presentation.forecast.models

import com.alexius.weatherio.common.utils.AppError
import com.alexius.weatherio.domain.models.forecast.DailyWeatherInfo
import com.alexius.weatherio.domain.models.forecast.Weather
import com.alexius.weatherio.domain.models.home.Geolocation

data class ForecastState(
    val weather: Weather? = null,
    val error: AppError? = null,
    val isLoading: Boolean = false,
    val dailyWeatherInfo: DailyWeatherInfo? = null,
    val selectedLocation: Geolocation? = null
)
