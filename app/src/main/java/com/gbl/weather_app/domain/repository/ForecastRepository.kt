package com.gbl.weather_app.domain.repository

import com.gbl.weather_app.domain.model.Forecast

interface ForecastRepository {
    suspend fun getForecast(locationId: Int): Forecast
    suspend fun deleteForecast(locationId: Int)
}