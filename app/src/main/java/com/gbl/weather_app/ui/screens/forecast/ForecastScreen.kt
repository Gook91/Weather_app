package com.gbl.weather_app.ui.screens.forecast

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gbl.weather_app.R
import com.gbl.weather_app.domain.model.Forecast
import com.gbl.weather_app.domain.model.Location
import com.gbl.weather_app.domain.model.WeatherByDate

@Composable
fun ForecastScreen(
    forecast: Forecast,
    onDelete: () -> Unit,
    onBackNavigation: () -> Unit
) {
    Scaffold(
        topBar = {
            ForecastTopBar(
                title = forecast.location.name,
                onBackNavigation = onBackNavigation,
                onDelete = {
                    onDelete()
                    onBackNavigation()
                }
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = dimensionResource(id = R.dimen.screen_padding))
                .padding(top = dimensionResource(id = R.dimen.screen_padding))
        ) {
            items(forecast.weather) { weatherByDate ->
                WeatherByDate(weatherByDate)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastTopBar(
    title: String,
    onBackNavigation: () -> Unit,
    onDelete: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackNavigation) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_navigation)
                )
            }
        },
        title = {
            Text(
                text = title,
                modifier = Modifier.testTag(FORECAST_TOP_BAR_TITLE_TAG)
            )
        },
        actions = {
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = stringResource(id = R.string.delete_forecast)
                )
            }
        },
        windowInsets = WindowInsets(0.dp)
    )
}

@Preview
@Composable
private fun PreviewForecastScreen() {
    ForecastScreen(
        forecast = object : Forecast {
            override val location: Location = object : Location {
                override val id: Int = 123
                override val name: String = "Location"
                override val latitude: Double = 0.0
                override val longitude: Double = 0.0
            }
            override val weather: List<WeatherByDate> = List(7) { id ->
                object : WeatherByDate {
                    override val date: String = "2024-09-1$id"
                    override val minTemperature: Double = 25.0 - id
                    override val maxTemperature: Double = 20.0 - id
                }
            }
        },
        onBackNavigation = {},
        onDelete = {}
    )
}

const val FORECAST_TOP_BAR_TITLE_TAG = "top_app_bar"