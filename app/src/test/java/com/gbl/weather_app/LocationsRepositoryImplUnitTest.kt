package com.gbl.weather_app

import com.gbl.weather_app.data.locations.InitialSearchQueryDataSource
import com.gbl.weather_app.data.locations.LocationLocalDataSource
import com.gbl.weather_app.data.locations.LocationRemoteDataSource
import com.gbl.weather_app.data.locations.LocationsRepositoryImpl
import com.gbl.weather_app.data.sources.api.LocationApiModel
import com.gbl.weather_app.data.sources.db.LocationDbModel
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import java.lang.RuntimeException

class LocationsRepositoryImplUnitTest {
    private val remoteLocationId = 128
    private val localLocationId = 512

    private val remoteLocation = getLocation(remoteLocationId)
    private val remoteLocationsList = listOf(
        LocationApiModel(
            remoteLocation.id,
            remoteLocation.name,
            remoteLocation.latitude,
            remoteLocation.longitude
        )
    )
    private val localLocationsList = listOf(LocationDbModel(getLocation(localLocationId)))

    private val mockLocationRemoteDataSource = Mockito.mock(LocationRemoteDataSource::class.java)
    private val mockLocationLocalDataSource = Mockito.mock(LocationLocalDataSource::class.java)
    private val mockInitialSearchQueryDataSource =
        Mockito.mock(InitialSearchQueryDataSource::class.java)

    private val locationsRepositoryImpl = LocationsRepositoryImpl(
        mockLocationRemoteDataSource,
        mockLocationLocalDataSource,
        mockInitialSearchQueryDataSource
    )

    @Test
    fun get_local_saved_data_if_query_is_empty() {
        runBlocking {
            // Arrange
            Mockito
                .`when`(mockLocationRemoteDataSource.getLocations(Mockito.anyString()))
                .then { Thread.sleep(10_000L) }
                .thenReturn(remoteLocationsList)

            Mockito
                .`when`(mockLocationLocalDataSource.gelLocationsByQuery(Mockito.anyString()))
                .thenReturn(localLocationsList)
            Mockito
                .`when`(mockLocationLocalDataSource.getAllLocations())
                .thenReturn(emptyList())

            // Act
            val locations = locationsRepositoryImpl.getLocationsByQuery("")

            // Assert
            assert(locations.isEmpty())
        }
    }

    @Test
    fun get_data_from_local_if_server_reply_wait_too_long() {
        runBlocking {
            // Arrange
            Mockito
                .`when`(mockLocationRemoteDataSource.getLocations(Mockito.anyString()))
                .then { Thread.sleep(10_000L) }
                .thenReturn(remoteLocationsList)

            Mockito
                .`when`(mockLocationLocalDataSource.gelLocationsByQuery(Mockito.anyString()))
                .thenReturn(localLocationsList)

            // Act
            val locations = locationsRepositoryImpl.getLocationsByQuery("non null query")

            // Assert
            assert(locations.last().id == localLocationId)
        }
    }

    @Test
    fun get_data_from_local_if_server_response_error() {
        runBlocking {
            // Arrange
            Mockito
                .`when`(mockLocationRemoteDataSource.getLocations(Mockito.anyString()))
                .thenThrow(RuntimeException())

            Mockito
                .`when`(mockLocationLocalDataSource.gelLocationsByQuery(Mockito.anyString()))
                .thenReturn(localLocationsList)

            // Act
            val locations = locationsRepositoryImpl.getLocationsByQuery("non null query")

            // Assert
            assert(locations.last().id == localLocationId)
        }
    }
}