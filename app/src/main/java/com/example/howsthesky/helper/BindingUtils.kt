package com.example.howsthesky.helper

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.howsthesky.formatTemperatureString
import com.example.howsthesky.formatWeatherDescription

@BindingAdapter("cityName")
fun TextView.setCityName(item: Weather?) {
    item?.let {
        text = item.cityName
    }
}

@BindingAdapter("cityTemperature")
fun TextView.setCityTemperature(item: Weather?) {
    item?.let {
        text = formatTemperatureString(item.temperature)
    }
}

@BindingAdapter("weatherDescription")
fun TextView.setWeatherDescription(item: Weather?) {
    item?.let {
        text = formatWeatherDescription(item.weatherDescription)
    }
}