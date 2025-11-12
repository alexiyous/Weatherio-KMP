package com.alexius.weatherio.domain.mapper.forecast

import com.alexius.weatherio.common.utils.WeatherInfo
import com.alexius.weatherio.common.utils.formatUnixToDay
import com.alexius.weatherio.data.models.remote.forecast.DailyDto
import com.alexius.weatherio.domain.models.forecast.Daily
import com.alexius.weatherio.domain.models.forecast.DailyWeatherInfo

/*
fun DailyDto.toDomain(): Daily {
    return Daily(
        dailyWeatherInfo = this.apparentTemperatureMin.mapIndexed { index, temp ->
            DailyWeatherInfo(
                temperatureMax = this.apparentTemperatureMax[index],
                temperatureMin = temp,
                time = formatUnixToDay(this.time[index]),
                weatherStatus = WeatherInfo.getWeatherInfo(this.weatherCode[index]),
                windDirection = WeatherInfo.getWindDirection(this.[index]),
                windSpeed = ,
                sunrise = ,
                sunset = ,
                uvIndex =
            )
        }
    )
}*/
