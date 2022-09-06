package com.alphaomardiallo.exomindmeteoandroid.data.remote

import com.alphaomardiallo.exomindmeteoandroid.data.model.openWeather.ResponseCurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("data/2.5/weather?")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appID: String,
        @Query("units") units: String,
        @Query("lang") lang: String
    ): Response<ResponseCurrentWeather>
}