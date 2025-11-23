package com.alexius.weatherio.work

import com.alexius.weatherio.domain.notification.NotificationManager
import com.alexius.weatherio.domain.work.WorkScheduler
import com.alexius.weatherio.repository.ForecastRepository
import com.alexius.weatherio.repository.GeolocationRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.getString
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.time.Clock
import kotlin.time.Duration.Companion.minutes
import kotlin.time.ExperimentalTime

class DesktopWorkScheduler : WorkScheduler, KoinComponent {
    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private var job: Job? = null

    private val forecastRepository: ForecastRepository by inject()
    private val geolocationRepository: GeolocationRepository by inject()
    private val notificationManager: NotificationManager by inject()

    @OptIn(ExperimentalTime::class)
    override fun scheduleForecastSync() {
        if (job?.isActive == true) return

        job = scope.launch {
            while (isActive) {
                delay(30.minutes)
                try {
                    val geolocation = geolocationRepository.geolocation.firstOrNull() ?: continue

                    val response = forecastRepository.fetchWeatherData(
                        latitude = geolocation.latitude.toFloat(),
                        longitude = geolocation.longitude.toFloat(),
                        daily = arrayOf("weather_code"),
                        currentWeather = arrayOf("temperature_2m"),
                        hourlyWeather = arrayOf("weather_code", "temperature_2m"),
                        timeFormat = "unixtime",
                        timeZone = TimeZone.currentSystemDefault().id
                    )

                    response.fold(
                        onSuccess = { weather ->
                            val currentHour = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).hour
                            val nextHour = (currentHour + 1) % 24
                            val nextHourString = nextHour.toString().padStart(2, '0') + ":00"

                            val nextForecast = weather.hourly.hourlyInfoItem.find { it.time == nextHourString }

                            if (nextForecast != null) {
                                val description = getString(nextForecast.weatherStatus.info)
                                notificationManager.showForecastNotification(
                                    "Weather Forecast",
                                    "Next hour ($nextHourString): ${nextForecast.temperature}\u00B0C, $description"
                                )
                            }
                        },
                        onFailure = {}
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun cancelForecastSync() {
        job?.cancel()
    }
}
