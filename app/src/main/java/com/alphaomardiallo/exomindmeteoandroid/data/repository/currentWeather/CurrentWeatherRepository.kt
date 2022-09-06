package com.alphaomardiallo.exomindmeteoandroid.data.repository.currentWeather

import com.alphaomardiallo.exomindmeteoandroid.data.model.openWeather.ResponseCurrentWeather
import retrofit2.Response

interface CurrentWeatherRepository {
    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
        units: String,
        lang: String
    ): Response<ResponseCurrentWeather>
}