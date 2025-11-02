package com.alexius.weatherio.data.datasource.local

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<GeolocationDatabase>
}