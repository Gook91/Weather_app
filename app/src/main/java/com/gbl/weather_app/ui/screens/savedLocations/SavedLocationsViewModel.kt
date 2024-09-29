package com.gbl.weather_app.ui.screens.savedLocations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbl.weather_app.domain.model.Location
import com.gbl.weather_app.domain.useCases.GetSavedLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedLocationsViewModel @Inject constructor(
    private val getSavedLocationsUseCase: GetSavedLocationsUseCase
) : ViewModel() {

    private val _locationsFlow = MutableStateFlow<List<Location>>(emptyList()).also { updateList() }
    val locationsFlow: StateFlow<List<Location>> get() = _locationsFlow.asStateFlow()

    fun updateList() {
        viewModelScope.launch {
            val locations = getSavedLocationsUseCase.execute()
            _locationsFlow.value = locations
        }
    }
}