package com.example.howsthesky.helper

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.howsthesky.R
import com.example.howsthesky.convertImgIdToUri
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

@BindingAdapter("imageId")
fun bindImage(imgView: ImageView, imgId: String?) {
    imgId?.let {
        val imgUri = convertImgIdToUri(imgId)
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}
