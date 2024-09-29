package com.gbl.weather_app.integration.data

import com.gbl.weather_app.domain.model.Location
import com.gbl.weather_app.domain.repository.LocationsRepository

class FakeLocationsRepository : LocationsRepository {
    override suspend fun getLocationsByQuery(searchQuery: String): List<Location> {
        return if (searchQuery.isBlank())
            emptyList()
        else
            fakeLocations
    }

    override suspend fun getSavedLocations(): List<Location> = emptyList()

    override fun getInitialSearchQuery(): String = ""
}