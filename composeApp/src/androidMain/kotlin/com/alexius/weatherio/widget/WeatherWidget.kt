package com.alexius.weatherio.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.ColorFilter
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalSize
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.alexius.weatherio.MainActivity
import com.alexius.weatherio.R
import com.alexius.weatherio.presentation.forecast.components.DEGREE_SYMBOL
import com.alexius.weatherio.repository.ForecastRepository
import com.alexius.weatherio.repository.GeolocationRepository
import com.alexius.weatherio.widget.components.WeatherWidgetContentDetails
import com.alexius.weatherio.widget.components.WeatherWidgetLayout
import com.alexius.weatherio.widget.mapper.toAndroidDrawable
import com.alexius.weatherio.widget.mapper.toWidgetData
import com.alexius.weatherio.widget.models.CurrentWeatherWidgetData
import com.alexius.weatherio.widget.ui.WidgetTheme
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.first
import kotlinx.datetime.TimeZone
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WeatherWidget : GlanceAppWidget(), KoinComponent {

    private val forecastRepository: ForecastRepository by inject()
    private val geolocationRepository: GeolocationRepository by inject()

    override val sizeMode: SizeMode = SizeMode.Exact

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val weatherState by produceState<CurrentWeatherWidgetData?>(initialValue = null) {
                val location = geolocationRepository.geolocation.first()
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
                        value = it.currentWeather.toWidgetData()
                    }.onFailure {
                        Napier.wtf("Failed to load weather data.", it, tag = "WIDGET")
                        value = null
                    }
                }
            }

            WidgetTheme {
                WeatherWidgetContent(weatherState)
            }
        }
    }

    @Composable
    private fun WeatherWidgetContent(currentWeather: CurrentWeatherWidgetData?) {
        val size = LocalSize.current
        val width = size.width
        val height = size.height

        val showDetails = width >= 200.dp && height >= 200.dp
        val useRowLayout = height < 160.dp

        val iconSize = if (useRowLayout || !showDetails) 50.dp else 100.dp
        val textSize = if (useRowLayout || !showDetails) 30.sp else 60.sp


        val action = actionStartActivity(MainActivity::class.java)

        WeatherWidgetLayout(
            useRowLayout = useRowLayout,
            action = action,
            currentWeather = currentWeather
        ) {
            WeatherWidgetContentDetails(
                currentWeather = currentWeather!!,
                iconSize = iconSize,
                textSize = textSize,
                useRowLayout = useRowLayout,
                showDetails = showDetails
            )
        }
    }
}