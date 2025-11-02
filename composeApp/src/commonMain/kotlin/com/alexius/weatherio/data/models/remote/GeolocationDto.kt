package com.alexius.weatherio.data.models.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeolocationDto(
    @SerialName("generationtime_ms")
    val generationtimeMs: Double,
    @SerialName("geoLocationResults")
    val geolocationResults: List<GeolocationResult>
)