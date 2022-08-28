package com.example.weatherapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.ui.adapters.WeatherPagerAdapter
import com.example.weatherapp.ui.fragments.NewLocationDialogFragment
import com.example.weatherapp.ui.viewmodels.WeatherViewModel
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewPagerAdapter: WeatherPagerAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var dotsIndicator: DotsIndicator

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collectLatest {
                when (it.state) {
                    is WeatherViewModel.UiState.WeatherUiState.Success -> {
                        if (it.forecast.size > 0) {
                            setupViewPagerAdapter()
                        }
                    }
                    is WeatherViewModel.UiState.WeatherUiState.Empty -> {
                        val dialog = NewLocationDialogFragment()
                        dialog.show(supportFragmentManager, "show")
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun setupViewPagerAdapter() {
        viewPager2 = binding.viewPager
        viewPagerAdapter = WeatherPagerAdapter(
            this,
            viewModel.uiState.value.locations.size
        )
        viewPager2.adapter = viewPagerAdapter
        dotsIndicator = binding.dotsIndicator
        dotsIndicator.attachTo(viewPager2)
    }
}