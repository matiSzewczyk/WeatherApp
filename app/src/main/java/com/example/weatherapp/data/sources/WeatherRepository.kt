package com.example.weatherapp.data.sources

import com.example.weatherapp.data.models.weather.Weather
import com.example.weatherapp.data.models.weatherdb.Locations
import retrofit2.Response

interface WeatherRepository {
    suspend fun getForecast(location: String): Response<Weather>
    suspend fun saveLocation(location: String)
    suspend fun getLocations(): List<Locations>
}