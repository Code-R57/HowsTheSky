package com.example.howsthesky.currentweather

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.howsthesky.CityClass
import com.example.howsthesky.R
import com.example.howsthesky.convertImgIdToUri
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
                val imgUri = convertImgIdToUri(it.appIconId)
                val imgView = binding.weatherImage
                Glide.with(imgView.context)
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(imgView)
            }
        })

        val noInternetText = "Please Check Your Internet Connection and Try Again!!"
        val wrongCityText = "The location entered could not be found!"

        currentWeatherViewModel.noInternetStatus.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(context, noInternetText, Toast.LENGTH_LONG).show()
                currentWeatherViewModel.doneShowingNoInternetToast()
            }
        })

        currentWeatherViewModel.wrongCityEntered.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(context, wrongCityText, Toast.LENGTH_SHORT).show()
                currentWeatherViewModel.doneShowingWrongCityEnteredToast()
            }
        })

        binding.cityClass = cityClass

        binding.buttonCheckWeather.setOnClickListener { view: View ->

            updateCity(view)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.display_mode_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var modeString = item.toString()
        if (modeString == "Light Mode") {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        return true
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