package com.alphaomardiallo.exomindmeteoandroid.ui.resultFragment

import android.content.ContentValues.TAG
import android.util.Log
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

    private fun getCurrentWeather(city: City) {
        viewModelScope.launch {
            try {
                val response = currentWeatherRepository.getCurrentWeather(
                    latitude = city.latitude,
                    longitude = city.longitude,
                    units = "metric",
                    lang = "fr"
                )

                if (!response.isSuccessful) {
                    Log.e(TAG, "getCurrentWeather: api call unsuccessful", null)
                    return@launch
                }

                if (response.body() != null) updateCurrentWeather(response.body()!!) else Log.e(
                    TAG,
                    "getCurrentWeather: data is null",
                    null
                )
            } catch (exception: IOException) {
                Log.e(
                    TAG,
                    "getCurrentWeather: IOException = ${exception.message}",
                    null
                )
            } catch (exception: HttpException) {
                Log.e(
                    TAG,
                    "getCurrentWeather: HttpException = ${exception.message}",
                    null
                )
            }

            incrementCurrentProgress()
        }
    }

    private fun updateCurrentWeather(responseCurrentWeather: ResponseCurrentWeather) {
        Log.i(TAG, "updateCurrentWeather: ${responseCurrentWeather.weather}")
    }

    /**
     * button click
     */

    private var currentProgress = 0
    private val _apiCurrentProgress: MutableLiveData<Int> = MutableLiveData()
    val apiCurrentProgress: LiveData<Int> get() = _apiCurrentProgress

    fun getCurrentWeatherDataForEachCity() {
        viewModelScope.launch {
            runTimerMessage()
            for (city in cities) {
                getCurrentWeather(city)
                delay(10000)
            }
        }
    }

    private fun incrementCurrentProgress(){
        currentProgress += 20
        _apiCurrentProgress.value = currentProgress
        Log.i(TAG, "incrementCurrentProgress: ${apiCurrentProgress.value}")
    }

    /**
     * Text View handler
     */

    private val _messageToDisplay: MutableLiveData<Int> = MutableLiveData()
    val messageToDisplay: LiveData<Int> get() = _messageToDisplay
    
    private fun runTimerMessage() {
        val listMessageToDisplay = listOf(1,2,0,1,2,0,1,2,0)
        viewModelScope.launch {
            _messageToDisplay.value = 0
            for (index in listMessageToDisplay) {
                delay(6000)
                _messageToDisplay.value = index
                Log.i(TAG, "runTimerMessage: ${messageToDisplay.value}")
            }
        }
    }
}