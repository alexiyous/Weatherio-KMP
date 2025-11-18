package com.alexius.weatherio.data.mapper.forecast

import com.alexius.weatherio.common.utils.TimeUtils
import com.alexius.weatherio.common.utils.WeatherInfo
import com.alexius.weatherio.common.utils.WeatherInfoItem
import com.alexius.weatherio.data.models.remote.forecast.HourlyDto
import com.alexius.weatherio.domain.models.forecast.Hourly
import com.alexius.weatherio.domain.models.forecast.HourlyInfoItem

fun HourlyDto.toDomain(timezone: String): Hourly {
    return Hourly(
        hourlyInfoItem = this.time.mapIndexed { index, time ->
            HourlyInfoItem(
                temperature = this.temperature2m[index],
                time = TimeUtils.formatUnixToHour(time, timezone),
                weatherStatus = WeatherInfo.getWeatherInfo(this.weatherCode[index])
            )
        }
    )
}
