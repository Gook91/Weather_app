package com.gbl.weather_app.domain.model

interface Forecast {
    val location: Location
    val weather: List<WeatherByDate>
}