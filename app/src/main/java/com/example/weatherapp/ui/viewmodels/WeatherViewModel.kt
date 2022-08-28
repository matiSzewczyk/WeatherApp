package com.example.weatherapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.sources.WeatherRepository
import com.example.weatherapp.data.models.weather.Forecast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    data class UiState(
        val forecast: MutableList<MutableList<Forecast>> = mutableListOf(mutableListOf()),
        val locations: MutableList<String> = mutableListOf(),
        val state: WeatherUiState? = null
    ) {
        sealed class WeatherUiState {
            object Success : WeatherUiState()
            object IsLoading : WeatherUiState()
        }
    }

    init {
        viewModelScope.launch {
            grabLocationsFromDatabase()
            getForecast()
        }
    }

    private suspend fun grabLocationsFromDatabase() {
        val locations = repository.getLocations()

        locations.map {
            _uiState.value.locations.add(it.location!!)
        }
    }

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState.asStateFlow()

    private suspend fun getForecast() = viewModelScope.launch {

        uiState.value.locations.forEachIndexed { index, string ->
            val response = repository.getForecast(string)
            if (response.isSuccessful) {
                if (_uiState.value.forecast.size <= index) {
                    _uiState.value.forecast.add(mutableListOf())
                }
                response.body()?.forecast?.map { forecast ->
                    _uiState.value.forecast[index].add(forecast)
                }
                _uiState.update { uiState ->
                    uiState.copy(state = UiState.WeatherUiState.Success)
                }
            } else {
                Log.e(
                    "WeatherViewModel",
                    "getForecast: ${response.errorBody()?.string()}"
                )
            }
        }
    }
}