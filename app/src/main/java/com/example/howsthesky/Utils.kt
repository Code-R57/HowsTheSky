package com.example.howsthesky

import android.net.Uri
import androidx.core.net.toUri
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

fun formatTemperatureString(temperature: Double): String {
    return "$temperature °C"
}

fun formatWeatherDescription(weatherDescription: String): String {
    return weatherDescription
}

fun formatTextToCapitalize(string: String): String {
    val words = string.split(" ").toMutableList()
    var result = ""
    for(word in words){
        result += word.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } + " "
    }

    return result
}

fun convertImgIdToUri(imgId: String?): Uri {
    return "https://openweathermap.org/img/wn/${imgId}@2x.png".toUri()
}
