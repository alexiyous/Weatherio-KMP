package com.alexius.weatherio.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.alexius.weatherio.domain.notification.NotificationManager
import com.alexius.weatherio.repository.ForecastRepository
import com.alexius.weatherio.repository.GeolocationRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.getString
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ForecastWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params), KoinComponent {

    private val forecastRepository: ForecastRepository by inject()
    private val geolocationRepository: GeolocationRepository by inject()
    private val notificationManager: NotificationManager by inject()

    override suspend fun doWork(): Result {
        return try {
            val geolocation = geolocationRepository.geolocation.firstOrNull() ?: return Result.failure()

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
                    Result.success()
                },
                onFailure = {
                    Result.retry()
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }
}
