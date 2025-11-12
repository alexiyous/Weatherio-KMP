package com.alexius.weatherio.data.datasource.remote

import com.alexius.weatherio.domain.models.home.Geolocation

interface GeolocationRemoteApiService {
    suspend fun searchLocation(query: String): Result<List<Geolocation>>
}