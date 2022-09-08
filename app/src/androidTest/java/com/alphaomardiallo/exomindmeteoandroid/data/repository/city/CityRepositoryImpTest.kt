package com.alphaomardiallo.exomindmeteoandroid.data.repository.city

import com.alphaomardiallo.exomindmeteoandroid.data.model.city.City
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class CityRepositoryImpTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Inject
    lateinit var cityRepositoryImp: CityRepositoryImp

    @After
    fun tearDown() {

    }

    @Test
    fun isInjected() {
        assertThat(cityRepositoryImp).isNotNull()
    }

    @Test
    fun provideCorrectCityList() {
        val list = cityRepositoryImp.getCityList()
        val expectedList = listOf(
            City(1, "Rennes", 48.117318276455535, -1.6779352450051628),
            City(2, "Paris", 48.856760710238454, 2.3525025839756037),
            City(3, "Nantes", 47.21725, -1.55336),
            City(4, "Bordeaux", 44.83875685087825, -0.5850012088851255),
            City(5, "Lyon", 45.74846, 4.84671)
        )
        for (item in list) {
            assertThat(expectedList.contains(item)).isTrue()
        }
    }

}