package com.gbl.weather_app.data.sources.api

import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {
    @GET("/v1/search?language=ru")
    suspend fun getLocations(@Query("name") name: String): LocationsResponse

    @GET("/v1/get?language=ru")
    suspend fun getLocationById(@Query("id") id: Int): LocationApiModel
}