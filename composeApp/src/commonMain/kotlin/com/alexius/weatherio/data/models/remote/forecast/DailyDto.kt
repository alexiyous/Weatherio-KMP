package com.alexius.weatherio.data.models.remote.forecast


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyDto(
    @SerialName("temperature_2m_max")
    val temperature2mMax: List<Double> = listOf(),
    @SerialName("temperature_2m_min")
    val temperature2mMin: List<Double> = listOf(),
    @SerialName("sunrise")
    val sunrise: List<Long> = listOf(),
    @SerialName("sunset")
    val sunset: List<Long> = listOf(),
    @SerialName("time")
    val time: List<Long> = listOf(),
    @SerialName("uv_index_max")
    val uvIndexMax: List<Double> = listOf(),
    @SerialName("weather_code")
    val weatherCode: List<Int> = listOf(),
    @SerialName("wind_direction_10m_dominant")
    val windDirection10mDominant: List<Double> = listOf(),
    @SerialName("wind_speed_10m_max")
    val windSpeed10mMax: List<Double> = listOf()
)