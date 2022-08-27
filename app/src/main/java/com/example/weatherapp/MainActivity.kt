package com.example.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.ui.adapters.WeatherPagerAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewPagerAdapter: WeatherPagerAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var dotsIndicator: DotsIndicator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPagerAdapter()
    }

    private fun setupViewPagerAdapter() {
        viewPager2 = binding.viewPager
        viewPagerAdapter = WeatherPagerAdapter(this)
        viewPager2.adapter = viewPagerAdapter
        dotsIndicator = binding.dotsIndicator
        dotsIndicator.attachTo(viewPager2)
    }
}