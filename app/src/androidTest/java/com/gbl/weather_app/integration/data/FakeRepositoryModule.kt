package com.gbl.weather_app.integration.data

import com.gbl.weather_app.di.RepositoryModule
import com.gbl.weather_app.domain.repository.ForecastRepository
import com.gbl.weather_app.domain.repository.LocationsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
object FakeRepositoryModule {

    @Provides
    fun provideFakeLocationsRepository(): LocationsRepository = FakeLocationsRepository()

    @Provides
    fun provideFakeForecastRepository(): ForecastRepository = FakeForecastRepository()
}