package com.example.howsthesky.currentweather

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.howsthesky.formatTextToCapitalize
import com.example.howsthesky.helper.Weather
import com.example.howsthesky.helper.WeatherDao
import com.example.howsthesky.network.WeatherApi
import com.example.howsthesky.network.WeatherProperty
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.Exception

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

    val city = Weather()

    fun onCheckWeatherButtonClicked() {
        viewModelScope.launch {
            insert(city)
        }
    }

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _weatherData = MutableLiveData<WeatherProperty>()
    val weatherData: LiveData<WeatherProperty>
        get() = _weatherData

    private val _noInternetStatus = MutableLiveData<Boolean>()
    val noInternetStatus: LiveData<Boolean>
        get() = _noInternetStatus

    fun doneShowingToast() {
        _noInternetStatus.value = false
    }

    fun getWeatherDetail(cityName: String) {
        WeatherApi.retrofitService.getWeather(cityName).enqueue(object: Callback<WeatherProperty> {
            override fun onResponse(
                call: Call<WeatherProperty>,
                response: Response<WeatherProperty>
            ) {
                val weatherResult = response.body()
                city.cityName = formatTextToCapitalize(weatherResult!!.name)
                city.temperature = weatherResult.main.temp
                city.weatherDescription = formatTextToCapitalize(weatherResult.weather[0].description)
                _weatherData.value = weatherResult
                onCheckWeatherButtonClicked()
            }

            override fun onFailure(call: Call<WeatherProperty>, t: Throwable) {
                _noInternetStatus.value = true
            }

        })
    }

    val recentCity = database.getMostRecentCity()
}