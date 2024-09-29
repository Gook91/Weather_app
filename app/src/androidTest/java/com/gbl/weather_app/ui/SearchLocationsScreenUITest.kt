package com.gbl.weather_app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.gbl.weather_app.R
import com.gbl.weather_app.ui.screens.searchLocations.SEARCH_LOCATION_BOX_TAG
import com.gbl.weather_app.ui.screens.searchLocations.SearchLocationsScreen
import com.gbl.weather_app.ui.screens.searchLocations.SearchLocationsUiState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchLocationsScreenUITest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Composable
    private fun SearchLocationScreenContent() {
        val searchedQuery = remember { mutableStateOf("Some initial query") }
        val searchLocationsUiState = SearchLocationsUiState(
            locations = emptyList(),
            searchText = searchedQuery.value,
            setSearchText = { searchedQuery.value = it }
        )
        SearchLocationsScreen(
            searchLocationsUiState = searchLocationsUiState,
            onForecastScreenByLocation = {}
        )
    }

    @Test
    fun show_placeholder_if_data_not_saved_and_query_is_blank_test() {
        // Arrange
        val placeholderText = InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .getString(R.string.lets_search)
        composeTestRule.setContent {
            SearchLocationScreenContent()
        }

        // Act
        composeTestRule.onNodeWithTag(SEARCH_LOCATION_BOX_TAG).performTextReplacement("")

        // Assert
        composeTestRule.onNodeWithText(placeholderText).assertIsDisplayed()
    }

    @Test
    fun clear_search_box_with_trailing_button() {

        // Arrange
        val clearButtonDescription = InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .getString(R.string.clear_search_query)

        val blankTextPlaceholder = InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .getString(R.string.search_placeholder)

        composeTestRule.setContent {
            SearchLocationScreenContent()
        }

        // Act
        composeTestRule.onNodeWithContentDescription(clearButtonDescription).performClick()

        // Assert
        composeTestRule
            .onNodeWithTag(SEARCH_LOCATION_BOX_TAG)
            .assertTextEquals("", blankTextPlaceholder)
    }
}