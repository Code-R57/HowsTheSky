package com.example.howsthesky.network

import com.squareup.moshi.Json

data class WeatherProperty(
    @Json(name = "main") val mainWeatherData: Main,
    @Json(name = "name") val cityName: String,
    @Json(name = "weather") val weatherDescList: List<Weather>
)

data class Main(
    @Json(name = "temp") val temp: Double
)

data class Weather(
    @Json(name = "description") val description: String,
    @Json(name = "icon") val iconId: String
)
