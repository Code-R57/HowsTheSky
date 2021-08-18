package com.example.howsthesky.currentweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.howsthesky.CityClass
import com.example.howsthesky.R
import com.example.howsthesky.databinding.FragmentCurrentWeatherBinding
import com.example.howsthesky.helper.WeatherDatabase

class CurrentWeatherFragment : Fragment() {

    val cityClass: CityClass = CityClass()

    lateinit var binding: FragmentCurrentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_current_weather,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = WeatherDatabase.getInstance(application).weatherDao

        val viewModelFactory = CurrentWeatherViewModelFactory(dataSource, application)

        val currentWeatherViewModel =
            ViewModelProvider(this, viewModelFactory)
                .get(CurrentWeatherViewModel::class.java)

        binding.currentWeatherViewModel = currentWeatherViewModel

        binding.setLifecycleOwner(this)

        currentWeatherViewModel.navigateToRecentCities.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(
                    CurrentWeatherFragmentDirections
                        .actionCurrentWeatherFragmentToRecentCitiesFragment()
                )
                currentWeatherViewModel.doneNavigating()
            }
        })

        currentWeatherViewModel.recentCity.observe(viewLifecycleOwner, {
            it?.let {
                binding.cityText.text = it.cityName
                binding.weatherTempText.text = "${it.temperature} °C"
                binding.weatherDescText.text = it.weatherDescription
            }
        })

        val toastText = "Please Check Your Internet Connection and Try Again!!"

        currentWeatherViewModel.noInternetStatus.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()
                currentWeatherViewModel.doneShowingToast()
            }
        })

        binding.cityClass = cityClass

        binding.buttonCheckWeather.setOnClickListener { view: View ->

            updateCity(view)
        }

        return binding.root
    }

    private fun updateCity(view: View) {
        binding.apply {
            var cityNameInput = cityEditText.text.toString()
            if (cityNameInput != "") {
                currentWeatherViewModel!!.getWeatherDetail(cityNameInput)
                cityEditText.text!!.clear()
                invalidateAll()
            }
        }
    }

}