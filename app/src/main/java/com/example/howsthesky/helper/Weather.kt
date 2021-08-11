package com.example.howsthesky.helper

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_data")
data class Weather(

    @PrimaryKey
    @ColumnInfo(name = "city_name")
    var cityName: String = "",

    @ColumnInfo(name = "temperature")
    var temperature: Double = 25.0,

    @ColumnInfo(name = "weather_description")
    var weatherDescription: String = "Thunderstorm"
)