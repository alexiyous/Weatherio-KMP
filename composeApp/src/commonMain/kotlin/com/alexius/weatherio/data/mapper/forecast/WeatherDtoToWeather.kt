package com.alexius.weatherio.data.mapper.forecast

import com.alexius.weatherio.data.models.remote.forecast.WeatherDto
import com.alexius.weatherio.domain.models.forecast.Weather

fun WeatherDto.toDomain(): Weather {
    return Weather(
        currentWeather = this.currentDto.toDomain(this.timezone),
        daily = this.dailyDto.toDomain(this.timezone),
        hourly = this.hourlyDto.toDomain(this.timezone),
        timezone = this.timezone
    )
}