package com.alexius.weatherio.repository

import com.alexius.weatherio.domain.models.Geolocation
import kotlinx.coroutines.flow.Flow

interface GeolocationRepository {
    val geolocation: Flow<Geolocation?>
    suspend fun upsertGeolocation(geolocation: Geolocation)
    suspend fun fetchGeolocation(query: String): Result<List<Geolocation>>
    suspend fun clearGeolocation()
    suspend fun clear()
}