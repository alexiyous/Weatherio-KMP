package com.alexius.weatherio.data.datasource.remote.forecast

import com.alexius.weatherio.common.network.safeApiCall
import com.alexius.weatherio.common.utils.ApiParameters
import com.alexius.weatherio.common.utils.Endpoints
import com.alexius.weatherio.data.mapper.forecast.toDomain
import com.alexius.weatherio.data.models.remote.forecast.WeatherDto
import com.alexius.weatherio.domain.models.forecast.Weather
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
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
            parameter(ApiParameters.LATITUDE, latitude.toString())
            parameter(ApiParameters.LONGITUDE, longitude.toString())
            parameter(ApiParameters.DAILY, daily.joinToString(","))
            parameter(ApiParameters.CURRENT_WEATHER, currentWeather.joinToString(","))
            parameter(ApiParameters.HOURLY, hourlyWeather.joinToString(","))
            parameter(ApiParameters.TIME_FORMAT, timeFormat)
            timeZone?.let { parameter(ApiParameters.TIMEZONE, timeZone) }
        }
    }

}
