package com.alexius.weatherio.domain.work

interface WorkScheduler {
    fun scheduleForecastSync()
    fun cancelForecastSync()
}
