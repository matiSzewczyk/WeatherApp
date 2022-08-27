package com.example.weatherapp.data.models.weather

data class Forecast(
    val avg_temp_c: Double,
    val avg_temp_f: Double,
    val chance_of_rain: Int,
    val condition: String,
    val date: String,
    val icon_url: String,
    val max_temp_c: Double,
    val max_temp_f: Double,
    val max_wind_kph: Double,
    val max_wind_mph: Double,
    val min_temp_c: Double,
    val min_temp_f: Double,
    val sunrise: String,
    val sunset: String,
    val will_it_rain: Boolean
)