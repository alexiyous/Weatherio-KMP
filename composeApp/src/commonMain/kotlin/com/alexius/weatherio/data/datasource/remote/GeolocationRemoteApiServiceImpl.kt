package com.alexius.weatherio.data.datasource.remote

import com.alexius.weatherio.common.network.safeApiCall
import com.alexius.weatherio.data.mapper.toDomain
import com.alexius.weatherio.data.models.remote.GeolocationDto
import com.alexius.weatherio.domain.models.Geolocation
import com.alexius.weatherio.utils.Endpoints
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.http.path

class GeolocationRemoteApiServiceImpl(
    private val httpClient: HttpClient
): GeolocationRemoteApiService {
    override suspend fun searchLocation(query: String): Result<List<Geolocation>> {
        return httpClient.safeApiCall(GeolocationDto::toDomain) {
            url { path(Endpoints.GEO_CODING_BASE_URL, Endpoints.GEO_CODING_END_POINT) }
            parameter("name", query)
        }
    }
}