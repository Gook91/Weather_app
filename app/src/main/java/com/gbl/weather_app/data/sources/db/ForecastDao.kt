package com.gbl.weather_app.data.sources.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert

@Dao
interface ForecastDao {
    @Upsert
    suspend fun upsertForecast(locationDbModel: LocationDbModel, weather: List<WeatherDbModel>)

    @Query("SELECT * FROM locations WHERE id=:locationId")
    suspend fun getLocationById(locationId: Int): LocationDbModel?

    @Query("SELECT * FROM daily_forecast WHERE location_id=:locationId")
    suspend fun getWeatherByLocationId(locationId: Int): List<WeatherDbModel>

    @Query("DELETE FROM locations WHERE id=:locationId")
    suspend fun deleteLocation(locationId: Int)

    @Query("DELETE FROM daily_forecast WHERE location_id=:locationId")
    suspend fun deleteWeather(locationId: Int)

    @Transaction
    suspend fun deleteForecast(locationId: Int) {
        deleteLocation(locationId)
        deleteWeather(locationId)
    }


}