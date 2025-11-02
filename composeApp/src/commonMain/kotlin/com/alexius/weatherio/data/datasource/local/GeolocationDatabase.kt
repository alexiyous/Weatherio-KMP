package com.alexius.weatherio.data.datasource.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.alexius.weatherio.data.datasource.local.dao.GeolocationDao
import com.alexius.weatherio.data.models.locals.GeolocationEntity

@Database(entities = [GeolocationEntity::class], version = 1)
@ConstructedBy(GeolocationConstructor::class)
abstract class GeolocationDatabase: RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "geolocation.db"
    }

    abstract fun geolocationDao(): GeolocationDao
}

expect object GeolocationConstructor: RoomDatabaseConstructor<GeolocationDatabase> {
    override fun initialize(): GeolocationDatabase
}