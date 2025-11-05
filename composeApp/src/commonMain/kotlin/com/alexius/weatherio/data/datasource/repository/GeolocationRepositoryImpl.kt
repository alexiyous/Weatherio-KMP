package com.alexius.weatherio.data.datasource.repository

import com.alexius.weatherio.domain.models.Geolocation
import com.alexius.weatherio.repository.GeolocationRepository
import kotlinx.coroutines.flow.Flow

class GeolocationRepositoryImpl: GeolocationRepository {
    override val geolocation: Flow<Geolocation>
        get() = TODO("Not yet implemented")

    override suspend fun upsertGeolocation(geolocation: Geolocation) {
        TODO("Not yet implemented")
    }

    override fun fetchGeolocation(query: String): Flow<Result<List<Geolocation>>> {
        TODO("Not yet implemented")
    }

    override suspend fun clearGeolocation() {
        TODO("Not yet implemented")
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }
}