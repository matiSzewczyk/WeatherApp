package com.example.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.models.weather.Forecast
import com.example.weatherapp.databinding.WeatherItemBinding

class WeatherAdapter(
    private val weather: List<Forecast>
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
            itemDate.text = weather[position].date

            itemTemp.text = holder.itemView.context.getString(
                R.string.temperature,
                weather[position].max_temp_c.toString()
            )
            Glide.with(itemIcon.context)
                .load("https:" + weather[position].icon_url)
                .override(150, 150)
                .into(itemIcon)
        }
    }

    override fun getItemCount() = weather.size


}
