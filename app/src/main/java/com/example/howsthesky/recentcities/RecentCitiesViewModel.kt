package com.example.howsthesky.recentcities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.howsthesky.formatCityData
import com.example.howsthesky.helper.Weather
import com.example.howsthesky.helper.WeatherDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class RecentCitiesViewModel(
    val database: WeatherDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val cities = database.getAllCitiesWeather()

    val citiesString = Transformations.map(cities) { cities ->
        formatCityData(cities, application.resources)
    }

    private val _navigateToCurrentWeather = MutableLiveData<Boolean>()
    val navigateToCurrentWeather: LiveData<Boolean>
        get() = _navigateToCurrentWeather

    fun onCurrentWeatherButton() {
        _navigateToCurrentWeather.value = true
    }

    fun doneNavigating() {
        _navigateToCurrentWeather.value = false
    }
}