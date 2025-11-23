package com.alexius.weatherio.domain.notification

interface NotificationManager {
    fun requestPermission()
    fun showForecastNotification(title: String, message: String)
}
