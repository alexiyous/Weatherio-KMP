package com.alexius.weatherio.data.datasource.remote.forecast

import com.alexius.weatherio.common.network.safeApiCall
import com.alexius.weatherio.common.utils.ApiParameters
import com.alexius.weatherio.common.utils.Endpoints
import com.alexius.weatherio.data.mapper.forecast.toDomain
import com.alexius.weatherio.data.models.remote.forecast.WeatherDto
import com.alexius.weatherio.domain.models.forecast.Weather
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.parameters

class ForecastRemoteApiServiceImpl(
    private val httpClient: HttpClient
): ForecastRemoteApiService {
    override suspend fun fetchForecast(
        latitude: Float,
        longitude: Float,
        daily: Array<String>,
        currentWeather: Array<String>,
        hourlyWeather: Array<String>,
        timeFormat: String,
        timeZone: String?
    ): Result<Weather> {
        return httpClient.safeApiCall(WeatherDto::toDomain) {
            url("${Endpoints.FORECAST_BASE_URL}${Endpoints.FORECAST_END_POINT}")
            parameters {
                append(ApiParameters.LATITUDE, latitude.toString())
                append(ApiParameters.LONGITUDE, longitude.toString())
                append(ApiParameters.DAILY, daily.joinToString(","))
                append(ApiParameters.CURRENT_WEATHER, currentWeather.joinToString(","))
                append(ApiParameters.HOURLY, hourlyWeather.joinToString(","))
                append(ApiParameters.TIME_FORMAT, timeFormat)
                timeZone?.let { this.append(ApiParameters.TIMEZONE, timeZone)}
            }
        }
    }

}
