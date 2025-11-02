package com.alexius.weatherio.data.models.locals

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("geolocation_table")
data class GeoLocationEntity(
    @PrimaryKey()
    val id: Int = 1,
    val name: String,
    val countryName: String,
    val countryCode: String,
    val countryId: Int,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val elevation: Double
)
