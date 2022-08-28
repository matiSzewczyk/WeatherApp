package com.example.weatherapp.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weatherapp.MainActivity
import com.example.weatherapp.ui.fragments.WeatherFragment

class WeatherPagerAdapter(
    activity: MainActivity,
    private val size: Int
) : FragmentStateAdapter(activity) {

    private val POS_ARG = "position"

    override fun getItemCount(): Int {
        return size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = WeatherFragment()
        fragment.arguments = Bundle().apply {
            putInt(POS_ARG, position)
        }
        return fragment
    }

}
