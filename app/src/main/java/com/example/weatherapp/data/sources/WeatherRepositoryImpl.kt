package com.example.weatherapp.data.sources

import com.example.weatherapp.data.models.weather.Weather
import com.example.weatherapp.data.models.weatherdb.Locations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDAO: WeatherDAO
) : WeatherRepository{

    override suspend fun getForecast(location: String): Response<Weather> {
        return withContext(Dispatchers.IO) {
            weatherApi.getForecast(location)
        }
    }

    override suspend fun saveLocation(location: Locations) {
        weatherDAO.saveLocation(Locations(location = location.location))
    }

    override suspend fun getLocations(): List<Locations> {
        return withContext(Dispatchers.IO) {
            weatherDAO.getLocations()
        }
    }
}