package com.gbl.weather_app.di

import android.content.Context
import com.gbl.weather_app.data.sources.db.AppDatabase
import com.gbl.weather_app.data.sources.db.ForecastDao
import com.gbl.weather_app.data.sources.db.LocationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getInstance(context)

    @Provides
    fun provideLocationsDao(appDatabase: AppDatabase): LocationDao =
        appDatabase.locationsDao()

    @Provides
    fun provideForecastDao(appDatabase: AppDatabase): ForecastDao =
        appDatabase.forecastDao()
}