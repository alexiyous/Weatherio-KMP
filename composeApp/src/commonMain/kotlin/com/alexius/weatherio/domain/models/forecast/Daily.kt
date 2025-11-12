package com.alexius.weatherio.domain.models.forecast

import com.alexius.weatherio.common.utils.WeatherInfoItem

data class Daily(
    val dailyWeatherInfo: List<DailyWeatherInfo>
)

data class DailyWeatherInfo(
    val temperatureMax: Double,
    val temperatureMin: Double,
    val time: String,
    val weatherStatus: WeatherInfoItem,
    val windDirection: String,
    val windSpeed: Double,
    val sunrise: String,
    val sunset: String,
    val uvIndex: Double
)
