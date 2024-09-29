package com.gbl.weather_app.domain.useCases

import com.gbl.weather_app.domain.repository.LocationsRepository
import javax.inject.Inject

class GetInitialSearchQueryUseCase @Inject constructor(
    private val locationsRepository: LocationsRepository
) {
    fun execute(): String = locationsRepository.getInitialSearchQuery()
}