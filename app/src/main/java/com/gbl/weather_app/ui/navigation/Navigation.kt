package com.gbl.weather_app.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.gbl.weather_app.ui.screens.forecast.ForecastScreen
import com.gbl.weather_app.ui.screens.forecast.ForecastViewModel
import com.gbl.weather_app.ui.screens.savedLocations.SavedLocationsScreen
import com.gbl.weather_app.ui.screens.savedLocations.SavedLocationsViewModel
import com.gbl.weather_app.ui.screens.searchLocations.SearchLocationsScreen
import com.gbl.weather_app.ui.screens.searchLocations.SearchLocationsViewModel

fun NavGraphBuilder.searchLocationDestination(onNavigateToForecastScreen: (Int) -> Unit) {
    composable<SearchLocationsScreenRoute> {
        val viewModel: SearchLocationsViewModel = hiltViewModel()
        viewModel.updateQuery()
        val searchLocationsUiState = viewModel.searchLocationsUiState.collectAsState().value
        SearchLocationsScreen(
            searchLocationsUiState = searchLocationsUiState,
            onForecastScreenByLocation = onNavigateToForecastScreen
        )
    }
}

fun NavGraphBuilder.savedLocationsDestination(onNavigateToForecastScreen: (Int) -> Unit) {
    composable<SavedLocationsScreenRoute> {
        val viewModel: SavedLocationsViewModel = hiltViewModel()
        val locations = viewModel.locationsFlow.collectAsState().value
        viewModel.updateList()
        SavedLocationsScreen(
            locations = locations,
            onForecastScreenByLocation = onNavigateToForecastScreen
        )
    }
}

fun NavGraphBuilder.forecastScreenDestination(onNavigateToPreviousScreen: () -> Unit) {
    composable<ForecastScreenRoute> { backStackEntry ->
        val locationId = backStackEntry.toRoute<ForecastScreenRoute>().locationId
        val forecastViewModel: ForecastViewModel =
            hiltViewModel<ForecastViewModel, ForecastViewModel.Factory>(
                creationCallback = { factory -> factory.create(locationId) }
            )
        val forecast = forecastViewModel.forecastFlow.collectAsState().value
        forecast?.let {
            ForecastScreen(
                forecast = it,
                onDelete = forecastViewModel.deleteForecast,
                onBackNavigation = onNavigateToPreviousScreen
            )
        }
    }
}

fun NavController.navigateToForecastScreen(locationId: Int) {
    navigate(route = ForecastScreenRoute(locationId))
}