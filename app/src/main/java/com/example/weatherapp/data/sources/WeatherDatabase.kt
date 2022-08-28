package com.example.weatherapp.data.sources

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.data.models.weatherdb.Locations

@Database(
    entities = [Locations::class],
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun WeatherDao() : WeatherDAO
}