package com.alphaomardiallo.exomindmeteoandroid.data.repository.city

import com.alphaomardiallo.exomindmeteoandroid.data.model.city.City

interface CityRepository {

    fun getCityList(): List<City>

}