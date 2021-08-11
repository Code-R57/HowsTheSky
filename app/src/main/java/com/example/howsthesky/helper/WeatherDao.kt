package com.example.howsthesky.helper

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WeatherDao {

    @Insert
    fun insertCityWeather(weather: Weather)

    @Update
    fun update(weather: Weather)

    @Query("SELECT * FROM weather_data")
    fun getAllCitiesWeather(): LiveData<List<Weather>>
}