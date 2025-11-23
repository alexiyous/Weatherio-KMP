package com.alexius.weatherio.notification

import com.alexius.weatherio.domain.notification.NotificationManager
import platform.UserNotifications.*

class NativeNotificationManager : NotificationManager {
    override fun requestPermission() {
        val center = UNUserNotificationCenter.currentNotificationCenter()
        center.requestAuthorizationWithOptions(
            options = UNAuthorizationOptionAlert or UNAuthorizationOptionSound or UNAuthorizationOptionBadge
        ) { granted, error ->
            if (error != null) {
                println("Notification permission error: ${error.localizedDescription}")
            }
        }
    }

    override fun showForecastNotification(title: String, message: String) {
        val center = UNUserNotificationCenter.currentNotificationCenter()
        
        val content = UNMutableNotificationContent()
        content.setTitle(title)
        content.setBody(message)
        content.setSound(UNNotificationSound.defaultSound())

        val request = UNNotificationRequest.requestWithIdentifier(
            "ForecastNotification",
            content,
            null
        )

        center.addNotificationRequest(request) { error ->
            if (error != null) {
                println("Error showing notification: ${error.localizedDescription}")
            }
        }
    }
}
