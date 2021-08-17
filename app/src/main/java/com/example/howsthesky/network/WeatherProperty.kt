package com.example.howsthesky.network

data class WeatherProperty(
    val main: Main,
    val name: String,
    val weather: List<Weather>
)

data class Main(
    val temp: Double
)

data class Weather(
    val description: String,
    val icon: String,
    val main: String
)