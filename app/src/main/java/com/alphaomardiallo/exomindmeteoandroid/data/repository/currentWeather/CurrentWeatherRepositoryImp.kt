package com.alphaomardiallo.exomindmeteoandroid.data.repository.currentWeather

import com.alphaomardiallo.exomindmeteoandroid.BuildConfig
import com.alphaomardiallo.exomindmeteoandroid.data.model.openWeather.ResponseCurrentWeather
import com.alphaomardiallo.exomindmeteoandroid.data.remote.OpenWeatherApi
import retrofit2.Response
import javax.inject.Inject

class CurrentWeatherRepositoryImp @Inject constructor(
    private val openWeatherApi: OpenWeatherApi
) : CurrentWeatherRepository {
    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
        units: String,
        lang: String
    ): Response<ResponseCurrentWeather> {
        return openWeatherApi.getCurrentWeather(
            latitude = latitude,
            longitude = longitude,
            appID = BuildConfig.OPEN_WEATHER_API_KEY,
            units = units,
            lang = lang
        )
    }
}