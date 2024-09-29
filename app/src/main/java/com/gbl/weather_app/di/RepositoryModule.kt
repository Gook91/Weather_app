package com.gbl.weather_app.di

import com.gbl.weather_app.data.forecast.ForecastRepositoryImpl
import com.gbl.weather_app.data.locations.LocationsRepositoryImpl
import com.gbl.weather_app.domain.repository.ForecastRepository
import com.gbl.weather_app.domain.repository.LocationsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideLocationsRepository(
        locationsRepositoryImpl: LocationsRepositoryImpl
    ): LocationsRepository = locationsRepositoryImpl

    @Provides
    fun provideForecastRepository(
        forecastRepositoryImpl: ForecastRepositoryImpl
    ): ForecastRepository = forecastRepositoryImpl
}