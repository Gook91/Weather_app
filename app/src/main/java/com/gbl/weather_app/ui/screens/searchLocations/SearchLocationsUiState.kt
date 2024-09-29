package com.gbl.weather_app.ui.screens.searchLocations

import com.gbl.weather_app.domain.model.Location

data class SearchLocationsUiState(
    val searchText: String,
    val locations: List<Location>,
    val setSearchText: (String) -> Unit,
) {
    val isEmptySaveData: Boolean = searchText.isEmpty() && locations.isEmpty()
}
