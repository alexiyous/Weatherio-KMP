package com.alexius.weatherio.data.models.remote.forecast


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentDto(
    @SerialName("interval")
    val interval: Int = 0,
    @SerialName("is_day")
    val isDay: Int = 0,
    @SerialName("temperature_2m")
    val temperature2m: Int = 0,
    @SerialName("time")
    val time: Long = 0,
    @SerialName("wind_direction_10m")
    val windDirection10m: Int = 0,
    @SerialName("wind_speed_10m")
    val windSpeed10m: Double = 0.0
)