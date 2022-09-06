package com.alphaomardiallo.exomindmeteoandroid.data.repository.city

import com.alphaomardiallo.exomindmeteoandroid.data.model.city.City
import javax.inject.Inject

class CityRepositoryImp @Inject constructor() : CityRepository {

    /**
     * I should probably have used Room to store cities as in a real app the user might choose locations he is interested on getting the weather
     */
    override fun getCityList(): List<City> {
        return listOf(
            City(1, "Rennes", 48.1173, 1.6778),
            City(2, "Paris", 48.8566, 2.3522),
            City(3, "Nantes", 47.2184, 1.5536),
            City(4, "Bordeaux", 44.8378, 0.5792),
            City(5, "Lyon", 45.7640, 4.8357)
        )
    }
}