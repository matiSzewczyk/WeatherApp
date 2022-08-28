package com.example.weatherapp.data.models.sources

import com.example.weatherapp.data.models.weather.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository{

    override suspend fun getForecast(location: String): Response<Weather> {
        return withContext(Dispatchers.IO) {
            weatherApi.getForecast(location)
        }
    }
}