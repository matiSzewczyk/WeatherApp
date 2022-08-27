package com.example.weatherapp.data.models.weather

data class Weather(
    val country: String,
    val forecast: List<Forecast>,
    val latitude: Double,
    val local_time: String,
    val location: String,
    val longitude: Double,
    val region: String,
    val timezone: String
)