package com.gbl.weather_app.data.sources.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {
    @GET("/v1/forecast?daily=temperature_2m_max,temperature_2m_min")
    suspend fun getForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): ForecastResponse
}