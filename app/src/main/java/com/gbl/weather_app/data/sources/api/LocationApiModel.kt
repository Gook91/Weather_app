package com.gbl.weather_app.data.sources.api

import com.gbl.weather_app.domain.model.Location
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationsResponse(
    val results: List<LocationApiModel>
)

@JsonClass(generateAdapter = true)
data class LocationApiModel(
    override val id: Int,
    override val name: String,
    override val latitude: Double,
    override val longitude: Double,
) : Location