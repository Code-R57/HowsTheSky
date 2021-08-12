package com.example.howsthesky

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.howsthesky.helper.Weather

fun formatCityData(cities: List<Weather>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append("<h3>THE LIST OF CITIES SEARCHED</h3>")
        cities.forEach {
            append("<br>")
            append("<b> ${it.cityName} <\b><br>")
            append("<b>Temperature: </b>")
            append("\t ${it.temperature} °C<br>")
            append("<b>Weather: </b>")
            append("\t ${it.weatherDescription} <br>")
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

fun formatTemperatureString(temperature: Double): String {
    return "Temperature: $temperature °C"
}

fun formatWeatherDescription(weatherDescription: String): String {
    return "Weather $weatherDescription"
}

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)