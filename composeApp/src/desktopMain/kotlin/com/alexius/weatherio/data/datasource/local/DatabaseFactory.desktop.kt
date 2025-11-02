package com.alexius.weatherio.data.datasource.local

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<GeolocationDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val username = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "weatherio")
            os.contains("mac") -> File(username, "Library/Application Support/weatherio")
            else -> File(username, ".local/share/weatherio")
        }

        if (!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, GeolocationDatabase.DATABASE_NAME).absolutePath
        return Room.databaseBuilder(dbFile)
    }
}