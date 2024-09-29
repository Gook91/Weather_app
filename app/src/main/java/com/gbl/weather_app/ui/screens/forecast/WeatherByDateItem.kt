package com.gbl.weather_app.ui.screens.forecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gbl.weather_app.R
import com.gbl.weather_app.domain.model.WeatherByDate

@Composable
fun WeatherByDate(
    weatherByDate: WeatherByDate,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.item_vertical_padding))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.item_vertical_padding))
        ) {
            Text(text = weatherByDate.date)
            Text(
                text = stringResource(
                    id = R.string.temperature_from_to,
                    weatherByDate.minTemperature,
                    weatherByDate.maxTemperature
                )
            )
        }
    }
}

@Preview
@Composable
private fun PreviewWeatherByDateItem() {
    WeatherByDate(object : WeatherByDate {
        override val date: String = "2024-09-30"
        override val minTemperature: Double = -2.9
        override val maxTemperature: Double = 2.8
    })
}