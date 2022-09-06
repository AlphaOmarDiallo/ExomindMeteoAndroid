package com.alphaomardiallo.exomindmeteoandroid.di

import com.alphaomardiallo.exomindmeteoandroid.data.remote.OpenWeatherApi
import com.alphaomardiallo.exomindmeteoandroid.data.repository.currentWeather.CurrentWeatherRepository
import com.alphaomardiallo.exomindmeteoandroid.data.repository.currentWeather.CurrentWeatherRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CurrentWeatherModule {

    @Singleton
    @Provides
    fun provideCurrentWeatherRepository(openWeatherApi: OpenWeatherApi): CurrentWeatherRepository =
        CurrentWeatherRepositoryImp(openWeatherApi)
}