package com.gbl.weather_app.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.gbl.weather_app.R
import com.gbl.weather_app.domain.model.Location

@Composable
fun LocationItem(
    location: Location,
    onItemClick: () -> Unit
) {
    Card(onClick = onItemClick) {
        Text(
            text = location.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.item_vertical_padding))
        )
    }
}

@Preview
@Composable
private fun PreviewLocationItem() {
    LocationItem(
        location = object : Location {
            override val id = 123
            override val name: String = "Some location"
            override val latitude = 59.9
            override val longitude = 30.0
        },
        onItemClick = {})
}