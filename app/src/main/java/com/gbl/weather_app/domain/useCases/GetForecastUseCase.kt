package com.gbl.weather_app.domain.useCases

import com.gbl.weather_app.domain.repository.ForecastRepository
import com.gbl.weather_app.domain.model.Forecast
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository
) {
    suspend fun execute(locationId: Int): Forecast =
        forecastRepository.getForecast(locationId)
}