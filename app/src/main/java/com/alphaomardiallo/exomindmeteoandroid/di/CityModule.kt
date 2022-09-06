package com.alphaomardiallo.exomindmeteoandroid.di

import com.alphaomardiallo.exomindmeteoandroid.data.repository.city.CityRepository
import com.alphaomardiallo.exomindmeteoandroid.data.repository.city.CityRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CityModule {

    @Singleton
    @Provides
    fun provideCityRepository(): CityRepository = CityRepositoryImp()
}