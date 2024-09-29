package com.gbl.weather_app.domain.useCases

import com.gbl.weather_app.domain.repository.LocationsRepository
import com.gbl.weather_app.domain.model.Location
import javax.inject.Inject

class GetLocationsBySearchQueryUseCase @Inject constructor(
    private val locationsRepository: LocationsRepository
) {
    suspend fun execute(searchQuery: String): List<Location> =
        locationsRepository.getLocationsByQuery(searchQuery)
}