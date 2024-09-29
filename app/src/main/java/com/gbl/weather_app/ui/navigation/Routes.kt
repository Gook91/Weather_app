package com.gbl.weather_app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.gbl.weather_app.R
import kotlinx.serialization.Serializable

interface Route

@Serializable
object SearchLocationsScreenRoute : Route

@Serializable
object SavedLocationsScreenRoute : Route

@Serializable
data class ForecastScreenRoute(val locationId: Int) : Route

data class TopLevelRoute<T : Route>(
    val name: String,
    val route: T,
    val icon: ImageVector
)

@Composable
fun getTopLevelRoutes(): List<TopLevelRoute<Route>> = listOf(
    TopLevelRoute(
        stringResource(id = R.string.search_route_name),
        SearchLocationsScreenRoute,
        Icons.Filled.Search
    ),
    TopLevelRoute(
        stringResource(id = R.string.saved_locations_route_name),
        SavedLocationsScreenRoute,
        Icons.Filled.Place
    ),
)