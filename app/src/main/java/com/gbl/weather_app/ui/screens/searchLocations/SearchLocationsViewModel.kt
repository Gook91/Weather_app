package com.gbl.weather_app.ui.screens.searchLocations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbl.weather_app.domain.useCases.GetInitialSearchQueryUseCase
import com.gbl.weather_app.domain.useCases.GetLocationsBySearchQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchLocationsViewModel @Inject constructor(
    getInitialSearchQueryUseCase: GetInitialSearchQueryUseCase,
    private val getLocationsBySearchQueryUseCase: GetLocationsBySearchQueryUseCase
) : ViewModel() {

    private val _searchLocationsUiState = MutableStateFlow(
        SearchLocationsUiState(
            searchText = "",
            locations = emptyList(),
            setSearchText = { updateStateByQuery(it) }
        )
    )
    val searchLocationsUiState: StateFlow<SearchLocationsUiState> get() = _searchLocationsUiState.asStateFlow()

    private var searchJob: Job? = null

    fun updateQuery() {
        updateStateByQuery(searchLocationsUiState.value.searchText)
    }

    private fun updateStateByQuery(searchQuery: String) {
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            _searchLocationsUiState.update { it.copy(searchText = searchQuery) }

            val locations = getLocationsBySearchQueryUseCase.execute(searchQuery)
            _searchLocationsUiState.update { it.copy(locations = locations) }
        }
    }

    init {
        updateStateByQuery(getInitialSearchQueryUseCase.execute())
    }
}