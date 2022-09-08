package com.alphaomardiallo.exomindmeteoandroid.testDI

import com.alphaomardiallo.exomindmeteoandroid.data.remote.OpenWeatherApi
import com.alphaomardiallo.exomindmeteoandroid.data.repository.currentWeather.CurrentWeatherRepository
import com.alphaomardiallo.exomindmeteoandroid.data.repository.currentWeather.CurrentWeatherRepositoryImp
import com.alphaomardiallo.exomindmeteoandroid.di.CurrentWeatherModule
import com.alphaomardiallo.exomindmeteoandroid.di.OpenWeatherApiModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CurrentWeatherModule::class]
)
class CurrentWeatherModuleTest {

    @Singleton
    @Provides
    fun provideCurrentWeatherRepository(openWeatherApi: OpenWeatherApi): CurrentWeatherRepository =
        CurrentWeatherRepositoryImp(openWeatherApi)
}