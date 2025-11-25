package com.alexius.weatherio.widget

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.alexius.weatherio.repository.ForecastRepository
import com.alexius.weatherio.repository.GeolocationRepository
import com.alexius.weatherio.widget.mapper.toWidgetData
import com.alexius.weatherio.widget.models.CurrentWeatherWidgetData
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.first
import kotlinx.datetime.TimeZone

val Context.weatherWidgetStore by preferencesDataStore("WeatherWidget")

object WeatherWidgetDataStore {
    private val TEMPERATURE = doublePreferencesKey("temperature")
    private val TIME = stringPreferencesKey("time")
    private val WEATHER_STATUS_INFO = stringPreferencesKey("weather_status_info")
    private val WEATHER_CODE = intPreferencesKey("weather_code")
    private val WIND_INFO = stringPreferencesKey("wind_info")
    private val IS_DAY = booleanPreferencesKey("is_day")
    private val UV_INDEX = doublePreferencesKey("uv_index")
    private val USING_OLD_VALUE = booleanPreferencesKey("using_old_value")

    suspend fun updateWidgetData(
        context: Context,
        forecastRepository: ForecastRepository,
        geolocationRepository: GeolocationRepository
    ) {
        val store = context.weatherWidgetStore
        val location =   geolocationRepository.geolocation.first()
        if (location != null) {
            val result = forecastRepository.fetchWeatherData(
                latitude = location.latitude.toFloat(),
                longitude = location.longitude.toFloat(),
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
            )
            result.onSuccess {
                Napier.d("Success: ${it.currentWeather}", tag = "WIDGET")
                val widgetData = it.currentWeather.toWidgetData(it.daily)
                store.edit { prefs ->
                    prefs[TEMPERATURE] = widgetData.temperature
                    prefs[TIME] = widgetData.time
                    prefs[WEATHER_STATUS_INFO] = widgetData.weatherStatusInfo
                    prefs[WEATHER_CODE] = widgetData.weatherCode
                    prefs[WIND_INFO] = widgetData.windInfo
                    prefs[IS_DAY] = widgetData.isDay
                    prefs[UV_INDEX] = widgetData.uvIndex
                    prefs[USING_OLD_VALUE] = false
                }
                Napier.d("Widget data updated", tag = "WIDGET")
            }.onFailure {
                Napier.wtf("Failed to load weather data.", it, tag = "WIDGET")
                store.edit { prefs ->
                    prefs[USING_OLD_VALUE] = true
                }
            }
        }
    }

    fun prefsToWidgetData(prefs: Preferences): CurrentWeatherWidgetData? {
        val temp = prefs[TEMPERATURE] ?: return null
        Napier.d("Loading weather data from prefs $prefs", tag = "WIDGET")
        return CurrentWeatherWidgetData(
            temperature = temp,
            time = prefs[TIME] ?: "",
            weatherStatusInfo = prefs[WEATHER_STATUS_INFO] ?: "",
            weatherCode = prefs[WEATHER_CODE] ?: 0,
            windInfo = prefs[WIND_INFO] ?: "",
            isDay = prefs[IS_DAY] ?: true,
            uvIndex = prefs[UV_INDEX] ?: 0.0,
            usingOldValue = prefs[USING_OLD_VALUE] ?: false
        )
    }
}
