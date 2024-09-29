package com.gbl.weather_app.data.forecast

import com.gbl.weather_app.data.sources.db.ForecastDao
import com.gbl.weather_app.data.sources.db.WeatherDbModel
import com.gbl.weather_app.data.sources.db.LocationDbModel
import com.gbl.weather_app.domain.model.Forecast
import com.gbl.weather_app.domain.model.Location
import com.gbl.weather_app.domain.model.WeatherByDate
import javax.inject.Inject

class ForecastLocalDataSource @Inject constructor(
    private val forecastDao: ForecastDao
) {
    suspend fun getForecastByLocationId(id: Int): Forecast? {
        val location = forecastDao.getLocationById(id) ?: return null
        val weather = forecastDao.getWeatherByLocationId(id)

        val forecast = object : Forecast {
            override val location: Location = location
            override val weather: List<WeatherByDate> = weather
        }
        return forecast
    }

    suspend fun saveForecast(forecast: Forecast) {
        // Clear old weather before save new data
        forecastDao.deleteWeather(forecast.location.id)

        forecastDao.upsertForecast(
            LocationDbModel(forecast.location),
            forecast.weather.map { WeatherDbModel(forecast.location.id, it) }
        )
    }

    suspend fun deleteForecastByLocationId(locationId: Int) {
        forecastDao.deleteForecast(locationId)
    }
}