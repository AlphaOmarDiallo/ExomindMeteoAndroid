package com.alphaomardiallo.exomindmeteoandroid.testDI

import com.alphaomardiallo.exomindmeteoandroid.data.repository.city.CityRepository
import com.alphaomardiallo.exomindmeteoandroid.data.repository.city.CityRepositoryImp
import com.alphaomardiallo.exomindmeteoandroid.di.CityModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CityModule::class]
)
class CityModuleTest {

    @Singleton
    @Provides
    fun provideCityRepository(): CityRepository = CityRepositoryImp()
}