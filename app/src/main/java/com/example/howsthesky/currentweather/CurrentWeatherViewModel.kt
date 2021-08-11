package com.example.howsthesky.currentweather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.howsthesky.helper.Weather
import com.example.howsthesky.helper.WeatherDao
import kotlinx.coroutines.*

class CurrentWeatherViewModel(
    val database: WeatherDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToRecentCities = MutableLiveData<Weather>()
    val navigateToRecentCities: LiveData<Weather>
        get() = _navigateToRecentCities

    fun doneNavigating() {
        _navigateToRecentCities.value = null
    }

    fun addNewCity() {
        uiScope.launch{
            val newCity = Weather()
            insert(newCity)
        }
    }

    private suspend fun insert(city: Weather) {
        return withContext(Dispatchers.IO) {
            database.insertCityWeather(city)
        }
    }

    fun updateCity() {
        uiScope.launch {
            val city = Weather()
            update(city)
        }
    }

    private suspend fun update(city: Weather) {
        withContext(Dispatchers.IO) {
            database.update(city)
        }
    }
}