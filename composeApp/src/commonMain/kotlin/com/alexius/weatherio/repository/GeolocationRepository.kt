package com.alexius.weatherio.repository

import com.alexius.weatherio.domain.models.Geolocation
import kotlinx.coroutines.flow.Flow

interface GeolocationRepository {
    val geolocation: Flow<Geolocation>
    suspend fun upsertGeolocation(geolocation: Geolocation)
    fun fetchGeolocation(query: String): Flow<Result<List<Geolocation>>>
    suspend fun clearGeolocation()
    suspend fun clear()
}