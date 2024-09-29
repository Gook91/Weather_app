package com.gbl.weather_app.di

import android.content.Context
import com.gbl.weather_app.data.sources.sharedPreferences.SharedPreferencesStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferencesStorage =
        SharedPreferencesStorage(context)
}