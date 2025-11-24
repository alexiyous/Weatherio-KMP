package com.alexius.weatherio.widget.mapper

import com.alexius.weatherio.R
import org.jetbrains.compose.resources.DrawableResource

fun Int.toAndroidDrawable(): Int {
    return when (this) {
        0 -> R.drawable.clear_sky
        1 -> R.drawable.mainly_clear
        2 -> R.drawable.mainly_clear
        3 -> R.drawable.over_cast
        45, 48 -> R.drawable.fog
        51, 53, 55 -> R.drawable.drizzle
        56, 57 -> R.drawable.freezing_drizzle
        61 -> R.drawable.rain_slight
        63, 65 -> R.drawable.rain_heavy
        66, 67 -> R.drawable.freezing_rain
        71 -> R.drawable.snow_fall_slight
        73 -> R.drawable.snow_fall_slight
        75, 77 -> R.drawable.snow_fall
        80, 81, 82 -> R.drawable.rain_slight
        85, 86 -> R.drawable.snow_fall_slight
        95, 96, 99 -> R.drawable.thunder_storm
        else -> R.drawable.clear_sky
    }
}