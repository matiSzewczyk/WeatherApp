package com.example.weatherapp.data.sources

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherapp.data.models.weatherdb.Locations

@Dao
interface WeatherDAO {

    @Insert
    suspend fun saveLocation(locations: Locations)

    @Query("SELECT * FROM locations")
    suspend fun getLocations(): MutableList<Locations>
}