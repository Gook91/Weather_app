package com.gbl.weather_app.data.sources.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.gbl.weather_app.data.sources.db.WeatherDbModel.Companion.DATE_COLUMN_NAME
import com.gbl.weather_app.data.sources.db.WeatherDbModel.Companion.LOCATION_ID_COLUMN_NAME
import com.gbl.weather_app.domain.model.WeatherByDate

@Entity(
    tableName = "daily_forecast",
    primaryKeys = [LOCATION_ID_COLUMN_NAME, DATE_COLUMN_NAME]
)
data class WeatherDbModel(
    @ColumnInfo(name = LOCATION_ID_COLUMN_NAME)
    val locationId: Int,
    @ColumnInfo(name = DATE_COLUMN_NAME)
    override val date: String,
    override val maxTemperature: Double,
    override val minTemperature: Double
) : WeatherByDate {
    constructor(locationId: Int, weatherByDate: WeatherByDate) : this(
        locationId,
        weatherByDate.date,
        weatherByDate.maxTemperature,
        weatherByDate.minTemperature
    )

    companion object {
        const val LOCATION_ID_COLUMN_NAME = "location_id"
        const val DATE_COLUMN_NAME = "date"
    }
}