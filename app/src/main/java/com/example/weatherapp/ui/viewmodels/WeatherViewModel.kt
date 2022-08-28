package com.example.weatherapp.ui.viewmodels

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
        val state: WeatherUiState? = null
    ) {
        sealed class WeatherUiState {
            object Success : WeatherUiState()
            object IsLoading : WeatherUiState()
        }
    }

    init {
        viewModelScope.launch {
            repository.saveLocation("London")
            repository.saveLocation("Spytkowice")
            val locations = repository.getLocations()
            println("locations: ${locations[0].location}")
            println("locations: ${locations[1].location}")
        }
    }

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState.asStateFlow()

    suspend fun getForecast() = viewModelScope.launch {
        val response = repository.getForecast("london")

        if (response.isSuccessful) {
            response.body()?.forecast?.map {
                _uiState.value.forecast[0].add(it)
            }
            _uiState.update {
                it.copy(state = UiState.WeatherUiState.Success)
            }
        }
    }
}