package com.gbl.weather_app.data.locations

import com.gbl.weather_app.domain.model.Location
import com.gbl.weather_app.domain.repository.LocationsRepository
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val locationRemoteDataSource: LocationRemoteDataSource,
    private val locationLocalDataSource: LocationLocalDataSource,
    private val initialSearchQueryDataSource: InitialSearchQueryDataSource
) : LocationsRepository {

    override suspend fun getLocationsByQuery(searchQuery: String): List<Location> {
        if (searchQuery.isEmpty()) return getSavedLocations()

        initialSearchQueryDataSource.setInitial(searchQuery)
        return try {
            withTimeout(5_000L) {
                locationRemoteDataSource.getLocations(searchQuery)
            }
        } catch (t: Throwable) {
            locationLocalDataSource.gelLocationsByQuery(searchQuery)
        }
    }

    override suspend fun getSavedLocations(): List<Location> =
        locationLocalDataSource.getAllLocations()

    override fun getInitialSearchQuery(): String = initialSearchQueryDataSource.getInitial()
}