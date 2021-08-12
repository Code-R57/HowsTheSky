package com.example.howsthesky.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.howsthesky.databinding.ListItemRecentCitiesBinding
import com.example.howsthesky.formatTemperatureString
import com.example.howsthesky.formatWeatherDescription

class RecentCitiesAdapter: ListAdapter<Weather, RecentCitiesAdapter.ViewHolder>(RecentCitiesDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemRecentCitiesBinding): RecyclerView.ViewHolder(binding.root) {
        val res = itemView.context.resources

        fun bind(item: Weather) {
            binding.cityWeather = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemRecentCitiesBinding
                    .inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class RecentCitiesDiffCallback : DiffUtil.ItemCallback<Weather>() {
    override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem.cityName == newItem.cityName
    }

    override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem == newItem
    }

}