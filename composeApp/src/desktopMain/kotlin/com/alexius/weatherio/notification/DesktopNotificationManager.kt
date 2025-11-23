package com.alexius.weatherio.notification

import com.alexius.weatherio.domain.notification.NotificationManager
import java.awt.SystemTray
import java.awt.TrayIcon
import java.awt.image.BufferedImage

class DesktopNotificationManager : NotificationManager {
    override fun requestPermission() {
        // Not typically required for desktop system tray
    }

    override fun showForecastNotification(title: String, message: String) {
        if (SystemTray.isSupported()) {
            val tray = SystemTray.getSystemTray()
            
            // Create a simple 1x1 image as icon since we don't have a file ready
            val image = BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB)
            // Fill with some color
            val g = image.createGraphics()
            g.color = java.awt.Color.BLUE
            g.fillRect(0, 0, 16, 16)
            g.dispose()
            
            val trayIcon = TrayIcon(image, "Weatherio")
            trayIcon.isImageAutoSize = true
            try {
                // Remove existing if any (simplification)
                tray.trayIcons.forEach { tray.remove(it) }
                
                tray.add(trayIcon)
                trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            println("System tray not supported: $title - $message")
        }
    }
}
