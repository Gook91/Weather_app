package com.gbl.weather_app.data.sources.api

import com.gbl.weather_app.domain.model.WeatherByDate
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponse(
    val daily: Daily
)

@JsonClass(generateAdapter = true)
data class Daily(
    val time: List<String>,
    @Json(name = "temperature_2m_max")
    val temperatureMax: List<Double>,
    @Json(name = "temperature_2m_min")
    val temperatureMin: List<Double>
) {
    fun mapToListWeatherByDateModel(): List<WeatherByDate> {
        val weatherSize = time.size
        val weatherList = List<WeatherByDate>(weatherSize) { index ->
            object : WeatherByDate {
                override val date = time[index]
                override val maxTemperature: Double = temperatureMax[index]
                override val minTemperature: Double = temperatureMin[index]
            }
        }
        return weatherList
    }
}
