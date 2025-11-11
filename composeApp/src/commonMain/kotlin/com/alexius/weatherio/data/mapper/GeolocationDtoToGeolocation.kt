package com.alexius.weatherio.data.mapper

import com.alexius.weatherio.data.models.remote.GeolocationDto
import com.alexius.weatherio.domain.models.Geolocation
import com.alexius.weatherio.common.utils.Endpoints

fun GeolocationDto.toDomain(): List<Geolocation> {
    return this.geolocationResults?.map {
        Geolocation(
            id = it.id,
            name = it.name,
            countryName = it.country,
            countryCode = it.countryCode,
            flagUrl = Endpoints.flagUrl(it.countryCode),
            countryId = it.countryId,
            latitude = it.latitude,
            longitude = it.longitude,
            timeZone = it.timezone ?: "",
            elevation = it.elevation ?: 0.0
        )
    } ?: emptyList()
}