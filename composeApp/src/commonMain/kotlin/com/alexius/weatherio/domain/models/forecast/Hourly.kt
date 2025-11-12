package com.alexius.weatherio.domain.models.forecast

import com.alexius.weatherio.common.utils.WeatherInfoItem

data class Hourly(
    val hourlyInfoItem: List<WeatherInfoItem>
)

data class HourlyInfoItem(
    val temperature: Double,
    val time: String,
    val weatherStatus: WeatherInfoItem
)