package com.alexius.weatherio.data.models.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoLocationDto(
    @SerialName("generationtime_ms")
    val generationtimeMs: Double,
    @SerialName("geoLocationResults")
    val geoLocationResults: List<GeoLocationResult>
)