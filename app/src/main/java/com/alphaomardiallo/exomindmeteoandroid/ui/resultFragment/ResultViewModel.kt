package com.alphaomardiallo.exomindmeteoandroid.ui.resultFragment

import android.content.ContentValues
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphaomardiallo.exomindmeteoandroid.data.model.city.City
import com.alphaomardiallo.exomindmeteoandroid.data.model.openWeather.ResponseCurrentWeather
import com.alphaomardiallo.exomindmeteoandroid.data.repository.city.CityRepository
import com.alphaomardiallo.exomindmeteoandroid.data.repository.currentWeather.CurrentWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val currentWeatherRepository: CurrentWeatherRepository,
    private val cityRepository: CityRepository
) : ViewModel() {

    /**
     * city repository
     */
    private val cities: List<City> = cityRepository.getCityList()

    /**
     * current weather
     */
    private val _currentWeatherList: MutableLiveData<MutableList<ResponseCurrentWeather>> =
        MutableLiveData()
    val currentWeatherList: LiveData<MutableList<ResponseCurrentWeather>> get() = _currentWeatherList

    fun getCurrentWeather(city: City) {
        viewModelScope.launch {
            try {
                val response = currentWeatherRepository.getCurrentWeather(
                    latitude = city.latitude,
                    longitude = city.longitude,
                    units = "metric",
                    lang = "fr"
                )

                if (!response.isSuccessful) {
                    Log.e(ContentValues.TAG, "getCurrentWeather: api call unsuccessful", null)
                    return@launch
                }

                if (response.body() != null) updateCurrentWeather(response.body()!!) else Log.e(
                    ContentValues.TAG,
                    "getCurrentWeather: data is null",
                    null
                )
            } catch (exception: IOException) {
                Log.e(
                    ContentValues.TAG,
                    "getCurrentWeather: IOException = ${exception.message}",
                    null
                )
            } catch (exception: HttpException) {
                Log.e(
                    ContentValues.TAG,
                    "getCurrentWeather: HttpException = ${exception.message}",
                    null
                )
            }
        }
    }

    private fun updateCurrentWeather(responseCurrentWeather: ResponseCurrentWeather) {
        _currentWeatherList.value!!.add(responseCurrentWeather)
    }

    /**
     * button click
     */

    suspend fun getCurrentWeatherDataForEachCity(){
       viewModelScope.launch {
           getCurrentWeather(cities[0])
           delay(10000)
           getCurrentWeather(cities[1])
           delay(10000)
           getCurrentWeather(cities[2])
           delay(10000)
           getCurrentWeather(cities[3])
           delay(10000)
           getCurrentWeather(cities[4])
       }
    }
}