@file:OptIn(ExperimentalTime::class)

package com.alexius.weatherio.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(FormatStringsInDatetimeFormats::class)
fun formatNormalDate(pattern: String, time: Long): String {
    val instant = Instant.fromEpochSeconds(time)
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val formatter = LocalDateTime.Format {
        byUnicodePattern(pattern)
    }
    return localDateTime.format(formatter)
}

fun formatUnixToHour(unixTimestamp: Long, timeZone: String = TimeZone.currentSystemDefault().id): String {
    val instant = Instant.fromEpochSeconds(unixTimestamp)
    val localDateTime = instant.toLocalDateTime(TimeZone.of(timeZone))

    val hour = localDateTime.hour.toString().padStart(2, '0')
    val minute = localDateTime.minute.toString().padStart(2, '0')
    return "$hour:$minute"
}


fun formatUnixToDay(unixTimestamp: Long, timeZone: String = TimeZone.currentSystemDefault().id): String {
    val instant = Instant.fromEpochSeconds(unixTimestamp)
    val localDateTime = instant.toLocalDateTime(TimeZone.of(timeZone))
    return localDateTime.dayOfWeek.name.capitalizeFirst()
}

fun formatUnixToCustom(unixTimestamp: Long, timeZone: String = TimeZone.currentSystemDefault().id): String {
    val instant = Instant.fromEpochSeconds(unixTimestamp)
    val localDateTime = instant.toLocalDateTime(TimeZone.of(timeZone))

    val month = localDateTime.month.name.capitalizeFirst()
    val day = localDateTime.dayOfMonth
    return "$month, $day"
}

fun isTodayDate(day: String): Boolean {
    val todayDate = formatUnixToDay(Clock.System.now().toEpochMilliseconds())
    return todayDate.equals(day, ignoreCase = true)
}