package com.alexius.weatherio.data.datasource.repository

import com.alexius.weatherio.data.datasource.local.dao.GeolocationDao
import com.alexius.weatherio.data.datasource.remote.GeolocationRemoteApiService
import com.alexius.weatherio.data.mapper.home.toDomain
import com.alexius.weatherio.domain.mapper.home.toDto
import com.alexius.weatherio.domain.models.home.Geolocation
import com.alexius.weatherio.repository.GeolocationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn

class GeolocationRepositoryImpl(
    private val geolocationRemoteApiService: GeolocationRemoteApiService,
    private val geolocationDao: GeolocationDao,
    private val externalScope: CoroutineScope
): GeolocationRepository {
    override val geolocation: Flow<Geolocation?>
        get() {
            return geolocationDao.getGeolocation().map { it?.toDomain() }
                .shareIn(
                    scope = externalScope,
                    started = SharingStarted.Lazily,
                    replay = 1
                )
        }

    override suspend fun upsertGeolocation(geolocation: Geolocation) {
        geolocationDao.upsertGeolocation(geolocation.toDto())
    }

    override suspend fun fetchGeolocation(query: String): Result<List<Geolocation>> {
        return runCatching {
            geolocationRemoteApiService.searchLocation(query).getOrThrow()
        }
    }

    override suspend fun clearGeolocation() {
        geolocationDao.clearGeolocation()
    }

    override suspend fun clear() {
        externalScope.cancel()
    }
}