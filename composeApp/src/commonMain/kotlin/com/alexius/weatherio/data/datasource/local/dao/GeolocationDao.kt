package com.alexius.weatherio.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.alexius.weatherio.data.models.locals.GeolocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GeolocationDao {
    @Upsert
    @Transaction
    suspend fun upsertGeolocation(geoLocationEntity: GeolocationEntity)

    @Query("Select * From geolocation_table Limit 1")
    fun getGeolocation(): Flow<GeolocationEntity>

    @Query("Delete From geolocation_table")
    @Transaction
    suspend fun clearGeolocation()
}