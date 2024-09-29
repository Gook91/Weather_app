package com.gbl.weather_app.integration

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import com.gbl.weather_app.integration.data.TARGET_LOCATION_NAME
import com.gbl.weather_app.ui.MainActivity
import com.gbl.weather_app.ui.navigation.NavScreen
import com.gbl.weather_app.ui.screens.forecast.FORECAST_TOP_BAR_TITLE_TAG
import com.gbl.weather_app.ui.screens.searchLocations.SEARCH_LOCATION_BOX_TAG
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SearchLocationsIntegrationTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun search_location_and_move_to_forecast() {

        // Arrange
        composeTestRule.activity.setContent {
            NavScreen()
        }

        // Act
        composeTestRule
            .onNodeWithTag(SEARCH_LOCATION_BOX_TAG)
            .performTextReplacement("Some query")
        composeTestRule
            .onNodeWithText(TARGET_LOCATION_NAME)
            .performClick()

        // Assert
        composeTestRule
            .onNodeWithTag(FORECAST_TOP_BAR_TITLE_TAG)
            .assertTextEquals(TARGET_LOCATION_NAME)

    }
}