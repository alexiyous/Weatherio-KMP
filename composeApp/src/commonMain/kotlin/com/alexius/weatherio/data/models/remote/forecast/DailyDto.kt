package com.alexius.weatherio.data.models.remote.forecast


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyDto(
    @SerialName("apparent_temperature_max")
    val apparentTemperatureMax: List<Double> = listOf(),
    @SerialName("apparent_temperature_min")
    val apparentTemperatureMin: List<Double> = listOf(),
    @SerialName("sunrise")
    val sunrise: List<String> = listOf(),
    @SerialName("sunset")
    val sunset: List<String> = listOf(),
    @SerialName("time")
    val time: List<Long> = listOf(),
    @SerialName("uv_index_max")
    val uvIndexMax: List<Double> = listOf(),
    @SerialName("weather_code")
    val weatherCode: List<Int> = listOf()
)