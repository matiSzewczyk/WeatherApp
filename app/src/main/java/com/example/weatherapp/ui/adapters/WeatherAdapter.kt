package com.example.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.models.weather.Forecast
import com.example.weatherapp.databinding.WeatherItemBinding
import com.example.weatherapp.utils.CustomClickInterface

class WeatherAdapter(
    private val weather: List<Forecast>,
    private val customClickListener: CustomClickInterface
) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    inner class WeatherViewHolder(
        val binding: WeatherItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            apply {
                itemView.setOnClickListener {

                    customClickListener.onClickListener(binding.expandedLayout)
                }
            }
        }
    }

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

            itemAvgTemp.text = holder.itemView.context.getString(
                R.string.avg_temperature,
                weather[position].avg_temp_c.toString()
            )
            Glide.with(itemIcon.context)
                .load("https:" + weather[position].icon_url)
                .override(150, 150)
                .into(itemIcon)

            itemMaxTemp.text = holder.itemView.context.getString(
                R.string.max_temperature,
                weather[position].max_temp_c.toString()
            )

            itemMinTemp.text = holder.itemView.context.getString(
                R.string.min_temperature,
                weather[position].min_temp_c.toString()
            )

            itemMaxWind.text = holder.itemView.context.getString(
                R.string.max_wind,
                weather[position].max_wind_kph.toString()
            )

            itemCondition.text = weather[position].condition

            itemChanceToRain.text = holder.itemView.context.getString(
                R.string.chance_to_rain,
                weather[position].chance_of_rain.toString()
            ) + "%"
            itemSunriseTime.text = weather[position].sunrise
            itemSunsetTime.text = weather[position].sunset
        }
    }

    override fun getItemCount() = weather.size


}
