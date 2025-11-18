package com.alexius.weatherio.data.mapper.forecast

import com.alexius.weatherio.common.utils.TimeUtils
import com.alexius.weatherio.common.utils.WeatherInfo
import com.alexius.weatherio.data.models.remote.forecast.CurrentDto
import com.alexius.weatherio.domain.models.forecast.CurrentWeather

fun CurrentDto.toDomain(timeZone: String): CurrentWeather {
    return CurrentWeather(
        temperature = this.temperature2m,
        time = TimeUtils.formatUnixToCustom(this.time, timeZone),
        weatherStatus = WeatherInfo.getWeatherInfo(this.weatherCode),
        windDirection = WeatherInfo.getWindDirection(this.windDirection10m),
        windSpeed = this.windSpeed10m,
        isDay = this.isDay == 1,
    )
}