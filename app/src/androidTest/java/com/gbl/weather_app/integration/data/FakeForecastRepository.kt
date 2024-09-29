package com.gbl.weather_app.integration.data

import com.gbl.weather_app.domain.model.Forecast
import com.gbl.weather_app.domain.model.Location
import com.gbl.weather_app.domain.model.WeatherByDate
import com.gbl.weather_app.domain.repository.ForecastRepository

class FakeForecastRepository : ForecastRepository {
    override suspend fun getForecast(locationId: Int): Forecast = object : Forecast {
        override val location: Location = fakeLocations[locationId]
        override val weather: List<WeatherByDate> = emptyList()
    }

    override suspend fun deleteForecast(locationId: Int) {}
}