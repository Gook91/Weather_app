package com.gbl.weather_app.data.sources.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gbl.weather_app.domain.model.Location

@Entity(tableName = "locations")
data class LocationDbModel(
    @PrimaryKey override val id: Int,
    override val name: String,
    override val latitude: Double,
    override val longitude: Double,
) : Location {
    constructor(location: Location) : this(
        id = location.id,
        name = location.name,
        latitude = location.latitude,
        longitude = location.longitude
    )
}