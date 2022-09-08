@file:OptIn(ExperimentalCoroutinesApi::class)

package com.alphaomardiallo.exomindmeteoandroid.ui.resultFragment

import com.alphaomardiallo.exomindmeteoandroid.data.repository.city.CityRepositoryImp
import com.alphaomardiallo.exomindmeteoandroid.data.repository.currentWeather.CurrentWeatherRepositoryImp
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class ResultViewModelTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Inject
    lateinit var cityRepositoryImp: CityRepositoryImp

    @Inject
    lateinit var currentWeatherImp: CurrentWeatherRepositoryImp

    @After
    fun tearDown() {

    }

    @Test
    fun isInjected() {
        assertThat(cityRepositoryImp).isNotNull()
        assertThat(currentWeatherImp).isNotNull()
    }

    @Test
    fun waitingMessageTrigger() = runTest {
        val listMessageToDisplay = listOf(1, 2, 0, 1, 2, 0, 1, 2, 0)
        val displayedMessage = mutableListOf<Int>()
        for (index in listMessageToDisplay) {
            displayedMessage.add(index)
        }

        for ((position, item) in displayedMessage.withIndex()) {
            assertThat(item == listMessageToDisplay[position]).isTrue()
        }
    }
}