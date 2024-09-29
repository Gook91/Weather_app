package com.gbl.weather_app.data.sources.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface LocationDao {
    @Query("SELECT * FROM locations WHERE name LIKE '%' || :searchQuery || '%'")
    suspend fun getLocationsByQuery(searchQuery: String): List<LocationDbModel>

    @Query("SELECT * FROM locations")
    suspend fun getAllLocations(): List<LocationDbModel>
}