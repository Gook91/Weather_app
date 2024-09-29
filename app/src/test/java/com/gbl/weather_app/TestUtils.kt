package com.gbl.weather_app

import com.gbl.weather_app.domain.model.Location
import kotlin.random.Random

fun getRandomTemperature(): Double = Random.nextInt(-300, 300) / 10.0

fun getLocation(requiredId: Int = 0): Location {
    val location: Location = object : Location {
        override val id: Int = requiredId
        override val name: String = "Some location"
        override val latitude: Double = 0.0
        override val longitude: Double = 0.0
    }
    return location
}

