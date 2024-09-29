package com.gbl.weather_app.data.forecast

import com.gbl.weather_app.data.sources.api.ForecastApi
import com.gbl.weather_app.data.sources.api.LocationApi
import com.gbl.weather_app.domain.model.Forecast
import com.gbl.weather_app.domain.model.Location
import com.gbl.weather_app.domain.model.WeatherByDate
import javax.inject.Inject

class ForecastRemoteDataSource @Inject constructor(
    private val forecastApi: ForecastApi,
    private val locationApi: LocationApi
) {
    suspend fun getForecastByLocationId(locationId: Int): Forecast {
        val location = locationApi.getLocationById(locationId)
        val dailyWeather = forecastApi.getForecast(location.latitude, location.longitude).daily

        val forecast = object : Forecast {
            override val location: Location = location
            override val weather: List<WeatherByDate> = dailyWeather.mapToListWeatherByDateModel()
        }
        return forecast
    }
}