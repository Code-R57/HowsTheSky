package com.example.howsthesky

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import com.example.howsthesky.databinding.FragmentCurrentWeatherBinding

class CurrentWeatherFragment : Fragment() {

    val cityClass: CityClass = CityClass()

    lateinit var binding: FragmentCurrentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_current_weather, container, false
        )
        binding.cityClass = cityClass
        binding.buttonCheckWeather.setOnClickListener { view: View ->
            updateCity(view)

        }

        binding.buttonToRecentCities.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_currentWeatherFragment_to_recentCitiesFragment)
        }
        return binding.root
    }

    private fun updateCity(view: View) {
        binding.apply {
            if (cityEditText.text.toString() != "") {
                cityClass?.city = cityEditText.text.toString()
                cityEditText.text!!.clear()
                invalidateAll()
            }
        }
    }

}