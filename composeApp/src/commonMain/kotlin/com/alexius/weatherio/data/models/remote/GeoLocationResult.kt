package com.alexius.weatherio.data.models.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoLocationResult(
    @SerialName("admin1")
    val admin1: String,
    @SerialName("admin1_id")
    val admin1Id: Int,
    @SerialName("admin2")
    val admin2: String,
    @SerialName("admin2_id")
    val admin2Id: Int,
    @SerialName("admin3")
    val admin3: String,
    @SerialName("admin3_id")
    val admin3Id: Int,
    @SerialName("admin4")
    val admin4: String,
    @SerialName("admin4_id")
    val admin4Id: Int,
    @SerialName("country")
    val country: String,
    @SerialName("country_code")
    val countryCode: String,
    @SerialName("country_id")
    val countryId: Int,
    @SerialName("elevation")
    val elevation: Int,
    @SerialName("feature_code")
    val featureCode: String,
    @SerialName("id")
    val id: Int,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("name")
    val name: String,
    @SerialName("population")
    val population: Int,
    @SerialName("postcodes")
    val postcodes: List<String>,
    @SerialName("timezone")
    val timezone: String
)