package com.alphaomardiallo.exomindmeteoandroid.testDI

import com.alphaomardiallo.exomindmeteoandroid.data.remote.OpenWeatherApi
import com.alphaomardiallo.exomindmeteoandroid.di.OpenWeatherApiModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [OpenWeatherApiModule::class]
)
class OpenWeatherApiModuleTest {
    private val baseUrl = "https://api.openweathermap.org/"

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
    }.build()

    @Singleton
    @Provides
    fun provideCurrentWeather(): OpenWeatherApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenWeatherApi::class.java)
    }
}