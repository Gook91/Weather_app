package com.gbl.weather_app.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.gbl.weather_app.R
import com.gbl.weather_app.domain.model.Forecast
import com.gbl.weather_app.domain.model.Location
import com.gbl.weather_app.domain.model.WeatherByDate
import com.gbl.weather_app.ui.screens.forecast.ForecastScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ForecastScreenUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun delete_saved_forecast_button_test() {
        // Arrange
        var isDeleteButtonClicked = false
        val deleteButtonDescription = InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .getString(R.string.delete_forecast)
        composeTestRule.setContent {
            ForecastScreen(
                forecast = object : Forecast {
                    override val location: Location = object : Location {
                        override val id: Int = 0
                        override val name: String = "Location"
                        override val latitude: Double = 0.0
                        override val longitude: Double = 0.0
                    }
                    override val weather: List<WeatherByDate> = emptyList()
                },
                onDelete = { isDeleteButtonClicked = true },
                onBackNavigation = {}
            )
        }

        // Act
        composeTestRule.onNodeWithContentDescription(deleteButtonDescription).performClick()

        // Assert
        assert(isDeleteButtonClicked)
    }
}