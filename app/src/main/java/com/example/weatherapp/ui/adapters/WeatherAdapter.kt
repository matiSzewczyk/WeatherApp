package com.example.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.models.weather.Weather
import com.example.weatherapp.databinding.WeatherItemBinding

class WeatherAdapter(
    private val weather: List<Weather>
) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    inner class WeatherViewHolder(
        val binding: WeatherItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            WeatherItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.binding.apply {
            itemDate.text = weather[position].forecast[position].date
            itemTemp.text = weather[position].forecast[position].max_temp_c.toString()
        }
    }

    override fun getItemCount() = weather.size


}
