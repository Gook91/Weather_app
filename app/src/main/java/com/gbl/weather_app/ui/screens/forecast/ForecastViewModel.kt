package com.gbl.weather_app.ui.screens.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbl.weather_app.domain.model.Forecast
import com.gbl.weather_app.domain.useCases.DeleteSavedForecastUseCase
import com.gbl.weather_app.domain.useCases.GetForecastUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = ForecastViewModel.Factory::class)
class ForecastViewModel @AssistedInject constructor(
    getForecastUseCase: GetForecastUseCase,
    private val deleteForecastUseCase: DeleteSavedForecastUseCase,
    @Assisted private val locationId: Int
) : ViewModel() {

    val forecastFlow = flow<Forecast?> {
        val forecast = getForecastUseCase.execute(locationId)
        emit(forecast)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000L),
        null
    )

    val deleteForecast: () -> Unit = {
        viewModelScope.launch {
            deleteForecastUseCase.execute(locationId)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(locationId: Int): ForecastViewModel
    }
}