package com.gbl.weather_app

import com.gbl.weather_app.data.forecast.ForecastLocalDataSource
import com.gbl.weather_app.data.sources.db.ForecastDao
import com.gbl.weather_app.data.sources.db.LocationDbModel
import com.gbl.weather_app.data.sources.db.WeatherDbModel
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

class ForecastLocalDataSourceUnitTest {

    @Test
    fun get_forecast_from_db() {
        runBlocking {
            // Arrange
            val id = 123
            val sizeForecast = 5
            val locationDbModel = LocationDbModel(getLocation(id))
            val forecastDbModel = List(sizeForecast) {
                WeatherDbModel(
                    id,
                    "2024_09_0${it + 1}",
                    getRandomTemperature(),
                    getRandomTemperature()
                )
            }
            val mockForecastDao = Mockito.mock(ForecastDao::class.java)
            Mockito.`when`(mockForecastDao.getLocationById(id)).thenReturn(locationDbModel)
            Mockito.`when`(mockForecastDao.getWeatherByLocationId(id)).thenReturn(forecastDbModel)

            val forecastLocalDataSource = ForecastLocalDataSource(mockForecastDao)

            // Act
            val forecast = forecastLocalDataSource.getForecastByLocationId(id)

            // Assert
            assert(
                forecast != null &&
                        forecast.location.id == id &&
                        forecast.weather.size == sizeForecast
            )
        }
    }
}