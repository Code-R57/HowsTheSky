package com.example.howsthesky.helper

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCityWeather(weather: Weather)

    @Update
    fun update(weather: Weather)

    @Query("SELECT * FROM weather_data ORDER BY city_name ASC")
    fun getAllCitiesWeather(): LiveData<List<Weather>>

    @Query("SELECT COUNT(city_name) FROM weather_data WHERE city_name = :cityName")
    fun containsCityName(cityName: String): LiveData<Int>

}