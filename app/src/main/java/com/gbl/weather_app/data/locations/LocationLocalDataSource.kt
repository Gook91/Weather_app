package com.gbl.weather_app.data.locations

import com.gbl.weather_app.data.sources.db.LocationDao
import com.gbl.weather_app.domain.model.Location
import javax.inject.Inject

class LocationLocalDataSource @Inject constructor(
    private val locationDao: LocationDao
) {
    suspend fun gelLocationsByQuery(searchQuery: String): List<Location> =
        locationDao.getLocationsByQuery(searchQuery)

    suspend fun getAllLocations(): List<Location> = locationDao.getAllLocations()
}