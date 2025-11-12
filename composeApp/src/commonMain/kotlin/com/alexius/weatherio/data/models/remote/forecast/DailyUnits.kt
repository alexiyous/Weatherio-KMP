package com.alexius.weatherio.data.models.remote.forecast


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyUnits(
    @SerialName("apparent_temperature_max")
    val apparentTemperatureMax: String = "",
    @SerialName("apparent_temperature_min")
    val apparentTemperatureMin: String = "",
    @SerialName("sunrise")
    val sunrise: String = "",
    @SerialName("sunset")
    val sunset: String = "",
    @SerialName("time")
    val time: String = "",
    @SerialName("uv_index_max")
    val uvIndexMax: String = "",
    @SerialName("weather_code")
    val weatherCode: String = ""
)