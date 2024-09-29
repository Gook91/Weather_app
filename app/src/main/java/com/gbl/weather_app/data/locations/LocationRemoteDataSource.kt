package com.gbl.weather_app.data.locations

import com.gbl.weather_app.data.sources.api.LocationApiModel
import com.gbl.weather_app.data.sources.api.LocationApi
import javax.inject.Inject

class LocationRemoteDataSource @Inject constructor(
    private val locationApi: LocationApi
) {
    suspend fun getLocations(name: String): List<LocationApiModel>{
        return if (name.length<2)
            emptyList()
        else {
            locationApi.getLocations(name).results
        }
    }
}