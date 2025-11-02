package com.alexius.weatherio.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.alexius.weatherio.data.models.locals.GeoLocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GeoLocationDao {
    @Upsert
    @Transaction
    suspend fun upsertGeoLocation(geoLocationEntity: GeoLocationEntity)

    @Query("Select * From geolocation_table Limit 1")
    fun getGeoLocation(): Flow<GeoLocationEntity>

    @Query("Delete From geolocation_table")
    @Transaction
    suspend fun clearGeoLocation()
}