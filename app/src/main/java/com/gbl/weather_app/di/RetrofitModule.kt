package com.gbl.weather_app.di

import com.gbl.weather_app.data.sources.api.ForecastApi
import com.gbl.weather_app.data.sources.api.LocationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideLocationsApi(): LocationApi =
        Retrofit.Builder()
            .baseUrl(LOCATIONS_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(LocationApi::class.java)

    @Provides
    fun provideForecastApi(): ForecastApi =
        Retrofit.Builder()
            .baseUrl(FORECAST_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ForecastApi::class.java)

    private const val LOCATIONS_BASE_URL = "https://geocoding-api.open-meteo.com"
    private const val FORECAST_BASE_URL = "https://api.open-meteo.com"
}