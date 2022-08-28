package com.example.weatherapp.data.sources

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.models.weather.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {

    @Headers("Authorization: Bearer ${BuildConfig.WEATHER_API_TOKEN}")
    @GET("forecast?days=7")
    suspend fun getForecast(
        @Query("location") location: String
    ): Response<Weather>
}