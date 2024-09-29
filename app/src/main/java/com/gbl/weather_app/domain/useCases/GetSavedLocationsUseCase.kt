package com.gbl.weather_app.domain.useCases

import com.gbl.weather_app.domain.repository.LocationsRepository
import com.gbl.weather_app.domain.model.Location
import javax.inject.Inject

class GetSavedLocationsUseCase @Inject constructor(
    private val locationsRepository: LocationsRepository
) {
    suspend fun execute(): List<Location> =
        locationsRepository.getSavedLocations()
}