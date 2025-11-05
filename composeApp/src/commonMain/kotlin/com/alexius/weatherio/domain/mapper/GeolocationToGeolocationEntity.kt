package com.alexius.weatherio.domain.mapper

import com.alexius.weatherio.data.models.locals.GeolocationEntity
import com.alexius.weatherio.domain.models.Geolocation

fun Geolocation.toDto(): GeolocationEntity = GeolocationEntity(
    id = this.id,
    name = this.name,
    countryName = this.countryName,
    countryCode = this.countryCode,
    countryId = this.countryId,
    latitude = this.latitude,
    longitude = this.longitude,
    timezone = this.timeZone,
    elevation = this.elevation
)