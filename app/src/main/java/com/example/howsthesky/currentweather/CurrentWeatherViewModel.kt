package com.example.howsthesky.currentweather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.howsthesky.helper.Weather
import com.example.howsthesky.helper.WeatherDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrentWeatherViewModel(
    val database: WeatherDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val _navigateToRecentCities = MutableLiveData<Boolean>()
    val navigateToRecentCities: LiveData<Boolean>
        get() = _navigateToRecentCities

    fun onRecentCitiesButtonClicked() {
        _navigateToRecentCities.value = true
    }

    fun doneNavigating() {
        _navigateToRecentCities.value = false
    }

    private suspend fun insert(city: Weather) {
        return withContext(Dispatchers.IO) {
            database.insertCityWeather(city)
        }
    }

    fun onCheckWeatherButtonClicked(cityName: String) {
        viewModelScope.launch {
            val city = Weather()
            city.cityName = cityName
            insert(city)
        }
    }
}