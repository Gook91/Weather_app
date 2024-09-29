package com.gbl.weather_app.domain.model

interface Location {
    val id: Int
    val name: String
    val latitude: Double
    val longitude: Double
}