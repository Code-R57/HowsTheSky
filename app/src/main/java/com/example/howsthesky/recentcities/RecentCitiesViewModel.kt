package com.example.howsthesky.recentcities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.howsthesky.helper.WeatherDao
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

    val cities = database.getAllCitiesWeather()

    private val _navigateToCurrentWeather = MutableLiveData<Boolean>()
    val navigateToCurrentWeather: LiveData<Boolean>
        get() = _navigateToCurrentWeather

    fun onCurrentWeatherButtonClicked() {
        _navigateToCurrentWeather.value = true
    }

    fun doneNavigating() {
        _navigateToCurrentWeather.value = false
    }
}