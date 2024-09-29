package com.gbl.weather_app.domain.model

interface WeatherByDate {
    val date: String
    val minTemperature: Double
    val maxTemperature: Double
}