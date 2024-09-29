package com.gbl.weather_app.data.forecast

import com.gbl.weather_app.domain.repository.ForecastRepository
import com.gbl.weather_app.domain.model.Forecast
import com.gbl.weather_app.domain.model.Location
import com.gbl.weather_app.domain.model.WeatherByDate
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val forecastRemoteDataSource: ForecastRemoteDataSource,
    private val forecastLocalDataSource: ForecastLocalDataSource
) : ForecastRepository {

    override suspend fun getForecast(locationId: Int): Forecast {

        val forecast: Forecast = try {
            withTimeout(5_000L) {
                forecastRemoteDataSource.getForecastByLocationId(locationId).also {
                    forecastLocalDataSource.saveForecast(it)
                }
            }
        } catch (t: Throwable) {
            forecastLocalDataSource.getForecastByLocationId(locationId)
                ?: object : Forecast {
                    override val location = object : Location {
                        override val id: Int = locationId
                        override val name: String = ""
                        override val latitude: Double = 0.0
                        override val longitude: Double = 0.0
                    }
                    override val weather = emptyList<WeatherByDate>()
                }
        }
        return forecast
    }

    override suspend fun deleteForecast(locationId: Int) {
        forecastLocalDataSource.deleteForecastByLocationId(locationId)
    }
}