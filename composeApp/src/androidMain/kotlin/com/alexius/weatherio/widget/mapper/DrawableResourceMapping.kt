package com.alexius.weatherio.widget.mapper

import com.alexius.weatherio.R
import org.jetbrains.compose.resources.DrawableResource

fun DrawableResource.toAndroidDrawable(): Int {
    return when (this.toString().substringAfterLast(".").substringBefore("'")) {
        "clear_sky" -> R.drawable.clear_sky
        "mainly_clear" -> R.drawable.mainly_clear
        "over_cast" -> R.drawable.over_cast
        "fog" -> R.drawable.fog
        "drizzle" -> R.drawable.drizzle
        "freezing_drizzle" -> R.drawable.freezing_drizzle
        "rain_slight" -> R.drawable.rain_slight
        "rain_heavy" -> R.drawable.rain_heavy
        "freezing_rain" -> R.drawable.freezing_rain
        "snow_fall_slight" -> R.drawable.snow_fall_slight
        "snow_fall" -> R.drawable.snow_fall
        "thunder_storm" -> R.drawable.thunder_storm
        else -> R.drawable.clear_sky // fallback
    }
}