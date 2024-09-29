package com.gbl.weather_app.ui.screens.savedLocations

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.gbl.weather_app.R
import com.gbl.weather_app.domain.model.Location
import com.gbl.weather_app.ui.views.EmptyDataPlaceholder
import com.gbl.weather_app.ui.views.LocationItem

@Composable
fun SavedLocationsScreen(
    locations: List<Location>,
    onForecastScreenByLocation: (Int) -> Unit
) {
    if (locations.isEmpty()) {
        EmptyDataPlaceholder()
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.screen_padding))
                .padding(top = dimensionResource(id = R.dimen.screen_padding))
        ) {
            items(locations) { location ->
                LocationItem(
                    location = location,
                    onItemClick = {
                        onForecastScreenByLocation(location.id)
                    })
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSavedLocationsScreen() {
    SavedLocationsScreen(
        locations = List(5) { id ->
            object : Location {
                override val id = id
                override val name = "Location N$id"
                override val latitude = 0.0
                override val longitude = 0.0
            }
        },
        onForecastScreenByLocation = {})
}
