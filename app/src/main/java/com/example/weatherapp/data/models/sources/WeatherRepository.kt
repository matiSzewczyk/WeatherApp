package com.example.weatherapp.data.models.sources

import com.example.weatherapp.data.models.weather.Weather
import retrofit2.Response

interface WeatherRepository {
    suspend fun getForecast(location: String): Response<Weather>
}