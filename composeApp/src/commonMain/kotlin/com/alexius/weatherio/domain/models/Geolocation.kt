package com.alexius.weatherio.domain.models

import com.alexius.weatherio.common.utils.FlagUrl

data class Geolocation(
    val id:Int = 0,
    val name:String,
    val countryName:String,
    val countryCode:String,
    val flagUrl: FlagUrl,
    val countryId:Int,
    val latitude:Double,
    val longitude:Double,
    val timeZone:String,
    val elevation: Double,
)