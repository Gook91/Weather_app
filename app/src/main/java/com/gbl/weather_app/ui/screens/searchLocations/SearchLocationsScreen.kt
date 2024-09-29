package com.gbl.weather_app.ui.screens.searchLocations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gbl.weather_app.R
import com.gbl.weather_app.domain.model.Location
import com.gbl.weather_app.ui.views.EmptyDataPlaceholder
import com.gbl.weather_app.ui.views.LocationItem

@Composable
fun SearchLocationsScreen(
    searchLocationsUiState: SearchLocationsUiState,
    onForecastScreenByLocation: (Int) -> Unit
) {
    Column {
        SearchField(searchLocationsUiState = searchLocationsUiState)

        if (searchLocationsUiState.isEmptySaveData) {
            EmptyDataPlaceholder()
        } else {
            LocationsColumn(
                locations = searchLocationsUiState.locations,
                onItemClick = onForecastScreenByLocation
            )
        }
    }
}

@Composable
private fun SearchField(searchLocationsUiState: SearchLocationsUiState) {
    OutlinedTextField(
        value = searchLocationsUiState.searchText,
        onValueChange = {
            searchLocationsUiState.setSearchText(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.item_vertical_padding))
            .testTag(SEARCH_LOCATION_BOX_TAG),
        placeholder = { Text(text = stringResource(id = R.string.search_placeholder)) },
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
        trailingIcon = {
            if (searchLocationsUiState.searchText.isNotBlank())
                IconButton(onClick = { searchLocationsUiState.setSearchText("") }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = stringResource(id = R.string.clear_search_query)
                    )
                }
        }
    )
}

@Composable
private fun LocationsColumn(
    locations: List<Location>,
    onItemClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.screen_padding))
            .padding(top = dimensionResource(id = R.dimen.screen_padding))
    ) {
        items(locations) { location ->
            LocationItem(
                location = location,
                onItemClick = { onItemClick(location.id) }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSavedLocationsScreen() {
    SearchLocationsScreen(
        SearchLocationsUiState(
            searchText = "",
            setSearchText = {},
            locations = List(5) { id ->
                object : Location {
                    override val id = id
                    override val name = "Location N$id"
                    override val latitude = 0.0
                    override val longitude = 0.0
                }
            }
        ),
        onForecastScreenByLocation = {}
    )
}

const val SEARCH_LOCATION_BOX_TAG = "search_location_box"