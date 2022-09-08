package com.alphaomardiallo.exomindmeteoandroid.data.repository.city

import com.alphaomardiallo.exomindmeteoandroid.data.model.city.City
import javax.inject.Inject

class CityRepositoryImp @Inject constructor() : CityRepository {

    /**
     * I should probably have used Room to store cities as in a real app the user might choose locations he is interested on getting the weather
     */
    override fun getCityList(): List<City> {
        return listOf(
            City(1, "Rennes", 48.117318276455535, -1.6779352450051628),
            City(2, "Paris", 48.856760710238454, 2.3525025839756037),
            City(3, "Nantes", 47.21725, -1.55336),
            City(4, "Bordeaux", 44.83875685087825, -0.5850012088851255),
            City(5, "Lyon", 45.74846, 4.84671)
        )
    }
}