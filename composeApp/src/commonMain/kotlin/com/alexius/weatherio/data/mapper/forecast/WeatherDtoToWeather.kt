package com.alexius.weatherio.data.mapper.forecast

import com.alexius.weatherio.data.models.remote.forecast.WeatherDto
import com.alexius.weatherio.domain.models.forecast.Weather

fun WeatherDto.toDomain(): Weather {
    return Weather(
        currentWeather = this.current.toDomain(this.timezone),
        daily = this.daily.toDomain(this.timezone),
        hourly = this.hourly.toDomain(this.timezone),
        timezone = this.timezone
    )
}