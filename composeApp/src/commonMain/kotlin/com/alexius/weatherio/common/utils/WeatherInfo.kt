package com.alexius.weatherio.common.utils

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import weatherio.composeapp.generated.resources.*

object WeatherInfo {
    private val windDirections = listOf(
        Res.string.wind_direction_north,
        Res.string.wind_direction_north_east,
        Res.string.wind_direction_east,
        Res.string.wind_direction_south_east,
        Res.string.wind_direction_south,
        Res.string.wind_direction_south_west,
        Res.string.wind_direction_west,
        Res.string.wind_direction_north_west
    )

    fun getWindDirection(degree: Double): StringResource {
        var normalized = degree % 360
        if (normalized < 0) normalized += 360

        val index = ((normalized + 22.5) / 45).toInt() % 8  // Add 22.5° offset
        return windDirections[index]
    }

    fun getWeatherInfo(code: Int): WeatherInfoItem {
        return when (code) {
            0 -> WeatherInfoItem(Res.string.weather_clear_sky, Res.drawable.clear_sky)
            1 -> WeatherInfoItem(Res.string.weather_mainly_clear, Res.drawable.mainly_clear)
            2 -> WeatherInfoItem(Res.string.weather_partly_cloudy, Res.drawable.mainly_clear)
            3 -> WeatherInfoItem(Res.string.weather_overcast, Res.drawable.over_cast)
            45, 48 -> WeatherInfoItem(Res.string.weather_fog, Res.drawable.fog)
            51, 53, 55 -> WeatherInfoItem(Res.string.weather_drizzle, Res.drawable.drizzle)
            56, 57 -> WeatherInfoItem(Res.string.weather_freezing_drizzle, Res.drawable.freezing_drizzle)
            61 -> WeatherInfoItem(Res.string.weather_rain_slight, Res.drawable.rain_slight)
            63 -> WeatherInfoItem(Res.string.weather_rain_moderate, Res.drawable.rain_heavy)
            65 -> WeatherInfoItem(Res.string.weather_rain_heavy, Res.drawable.rain_heavy)
            66, 67 -> WeatherInfoItem(Res.string.weather_freezing_rain, Res.drawable.freezing_rain)
            71 -> WeatherInfoItem(Res.string.weather_snow_fall_slight, Res.drawable.snow_fall_slight)
            73 -> WeatherInfoItem(Res.string.weather_snow_fall_moderate, Res.drawable.snow_fall_slight)
            75 -> WeatherInfoItem(Res.string.weather_snow_fall_heavy, Res.drawable.snow_fall)
            77 -> WeatherInfoItem(Res.string.weather_snow_grains, Res.drawable.snow_fall)
            80, 81, 82 -> WeatherInfoItem(Res.string.weather_rain_showers_slight, Res.drawable.rain_slight)
            85, 86 -> WeatherInfoItem(Res.string.weather_snow_showers_slight, Res.drawable.snow_fall_slight)
            95, 96, 99 -> WeatherInfoItem(Res.string.weather_thunderstorm_slight, Res.drawable.thunder_storm)
            else -> WeatherInfoItem(Res.string.weather_unknown, Res.drawable.clear_sky)
        }
    }
}

data class WeatherInfoItem(
    val info: StringResource,
    val icon: DrawableResource
)