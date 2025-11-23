package com.alexius.weatherio.presentation.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexius.weatherio.common.utils.TimeUtils
import com.alexius.weatherio.common.utils.toAppError
import com.alexius.weatherio.domain.models.home.Geolocation
import com.alexius.weatherio.presentation.forecast.models.ForecastState
import com.alexius.weatherio.repository.ForecastRepository
import com.alexius.weatherio.repository.GeolocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlin.math.abs
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class ForecastViewModel(
    private val forecastRepository: ForecastRepository,
    private val geolocationRepository: GeolocationRepository
): ViewModel() {

    private val _forecastState = MutableStateFlow(ForecastState())
    val forecastState = _forecastState.asStateFlow()

    init {
        viewModelScope.apply {
            launch { getGeolocation() }
        }
    }

    private suspend fun fetchWeatherData(geolocation: Geolocation?) {
        _forecastState.update { it.copy(isLoading = true) }
        geolocation?.let {
            forecastRepository.fetchWeatherData(
                latitude = it.latitude.toFloat(),
                longitude = it.longitude.toFloat(),
                daily = arrayOf(
                    "weather_code",
                    "temperature_2m_max",
                    "temperature_2m_min",
                    "wind_speed_10m_max",
                    "wind_direction_10m_dominant",
                    "sunrise",
                    "sunset",
                    "uv_index_max"
                ),
                currentWeather = arrayOf(
                    "temperature_2m",
                    "wind_speed_10m",
                    "wind_direction_10m",
                    "is_day",
                    "weather_code",
                ),
                hourlyWeather = arrayOf(
                    "weather_code",
                    "temperature_2m",
                ),
                timeFormat = "unixtime",
                timeZone = TimeZone.currentSystemDefault().id
            ).fold(
                onSuccess = { weather ->
                    
                    val todayWeatherInfo = weather.daily.dailyWeatherInfo.find { daily ->
                        TimeUtils.isTodayDate(daily.time)
                    }

                    _forecastState.update { state ->
                        state.copy(
                            weather = weather,
                            dailyWeatherInfo = todayWeatherInfo,
                            isLoading = false,
                            error = null
                        )
                    }
                },
                onFailure = { error ->
                    _forecastState.update { state ->
                        state.copy(
                            isLoading = false,
                            error = error.toAppError()
                        )
                    }
                }
            )
        }
    }

    private suspend fun getGeolocation() {
        geolocationRepository.geolocation.collect { geolocation ->
            fetchWeatherData(geolocation)
            _forecastState.update {
                it.copy(
                    selectedLocation = geolocation
                )
            }
        }
    }
}
