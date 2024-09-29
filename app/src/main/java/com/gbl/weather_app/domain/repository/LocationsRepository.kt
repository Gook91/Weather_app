package com.gbl.weather_app.domain.repository

import com.gbl.weather_app.domain.model.Location

interface LocationsRepository {
    suspend fun getLocationsByQuery(searchQuery: String): List<Location>

    suspend fun getSavedLocations(): List<Location>

    fun getInitialSearchQuery(): String
}