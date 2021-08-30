package com.example.howsthesky.recentcities

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.howsthesky.R
import com.example.howsthesky.databinding.FragmentRecentCitiesBinding
import com.example.howsthesky.helper.RecentCitiesAdapter
import com.example.howsthesky.helper.WeatherDatabase

class RecentCitiesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentRecentCitiesBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_recent_cities,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = WeatherDatabase.getInstance(application).weatherDao

        val viewModelFactory = RecentCitiesViewModelFactory(dataSource, application)

        val recentCitiesViewModel =
            ViewModelProvider(this, viewModelFactory)
                .get(RecentCitiesViewModel::class.java)

        binding.recentCitiesViewModel = recentCitiesViewModel

        val adapter = RecentCitiesAdapter()
        binding.recentCitiesList.adapter = adapter


        recentCitiesViewModel.cities.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.setLifecycleOwner(this)

        recentCitiesViewModel.navigateToCurrentWeather.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(
                    RecentCitiesFragmentDirections
                        .actionRecentCitiesFragmentToCurrentWeatherFragment()
                )
                recentCitiesViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}