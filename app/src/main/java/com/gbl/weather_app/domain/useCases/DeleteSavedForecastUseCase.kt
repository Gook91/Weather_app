package com.gbl.weather_app.domain.useCases

import com.gbl.weather_app.domain.repository.ForecastRepository
import javax.inject.Inject

class DeleteSavedForecastUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository
) {
    suspend fun execute(locationId: Int) =
        forecastRepository.deleteForecast(locationId)
}