package com.alexius.weatherio.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager as AndroidNotificationManagerSystem
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.alexius.weatherio.domain.notification.NotificationManager

class AndroidNotificationManager(
    private val context: Context
) : NotificationManager {

    override fun requestPermission() {
        // In Android, permission request should be handled by the Activity / UI layer.
        // This method is a placeholder or could trigger a prompt if implemented with Activity context.
    }

    override fun showForecastNotification(title: String, message: String) {
        val channelId = "weather_forecast_channel"
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Weather Forecast",
                AndroidNotificationManagerSystem.IMPORTANCE_DEFAULT
            )
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as AndroidNotificationManagerSystem
            manager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        
        NotificationManagerCompat.from(context).notify(1, notification)
    }
}
