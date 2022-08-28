package com.example.weatherapp.di

import com.example.weatherapp.data.models.sources.WeatherApi
import com.example.weatherapp.data.models.sources.WeatherRepository
import com.example.weatherapp.data.models.sources.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.m3o.com/v1/weather/forecast/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(weatherApi)
    }
}