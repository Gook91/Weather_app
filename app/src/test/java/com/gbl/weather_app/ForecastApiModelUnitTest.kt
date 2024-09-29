package com.gbl.weather_app

import com.gbl.weather_app.data.sources.api.Daily
import org.junit.Test

class ForecastApiModelUnitTest {

    @Test
    fun remote_model_correct_mapping() {
        // Arrange
        val size = 5

        val dates = List(size) { "2024_09_0${it + 1}" }
        val minTemperatures = List(size) { getRandomTemperature() }
        val maxTemperatures = List(size) { getRandomTemperature() }

        val forecastResponseDaily = Daily(dates, maxTemperatures, minTemperatures)

        // Act
        val weatherList = forecastResponseDaily.mapToListWeatherByDateModel()

        // Assert
        assert(weatherList.mapIndexed { i, weather ->
            weather.date == dates[i] &&
                    weather.maxTemperature == maxTemperatures[i] &&
                    weather.minTemperature == minTemperatures[i]
        }.all { it })
    }
}