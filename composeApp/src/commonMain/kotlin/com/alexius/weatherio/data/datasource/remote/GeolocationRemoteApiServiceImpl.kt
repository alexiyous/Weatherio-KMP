package com.alexius.weatherio.data.datasource.remote

import com.alexius.weatherio.common.network.safeApiCall
import com.alexius.weatherio.data.mapper.home.toDomain
import com.alexius.weatherio.data.models.remote.home.GeolocationDto
import com.alexius.weatherio.domain.models.home.Geolocation
import com.alexius.weatherio.common.utils.Endpoints
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class GeolocationRemoteApiServiceImpl(
    private val httpClient: HttpClient
): GeolocationRemoteApiService {
    override suspend fun searchLocation(query: String): Result<List<Geolocation>> {
        return httpClient.safeApiCall(GeolocationDto::toDomain) {
            url("${Endpoints.GEO_CODING_BASE_URL}${Endpoints.GEO_CODING_END_POINT}")
            parameter("name", query)
        }
    }
}