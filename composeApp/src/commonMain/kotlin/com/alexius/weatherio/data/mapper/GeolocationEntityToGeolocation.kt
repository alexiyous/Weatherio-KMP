package com.alexius.weatherio.data.mapper

import com.alexius.weatherio.data.models.locals.GeolocationEntity
import com.alexius.weatherio.domain.models.Geolocation
import com.alexius.weatherio.common.utils.Endpoints

fun GeolocationEntity.toDomain(): Geolocation = Geolocation(
    id = this.id,
    name = this.name,
    countryName = this.countryName,
    countryCode = this.countryCode,
    flagUrl = Endpoints.flagUrl(this.countryCode),
    countryId = this.countryId,
    latitude = this.latitude,
    longitude = this.longitude,
    timeZone = this.timezone,
    elevation = this.elevation,
)