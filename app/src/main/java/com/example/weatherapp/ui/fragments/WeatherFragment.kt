package com.example.weatherapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.ui.adapters.WeatherAdapter
import com.example.weatherapp.ui.viewmodels.WeatherViewModel
import kotlinx.coroutines.flow.collectLatest

class WeatherFragment : Fragment() {

    private val POS_ARG = "position"

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding

    private lateinit var weatherAdapter: WeatherAdapter

    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        setupRecyclerView()

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collectLatest {
                when (it.state) {
                    is WeatherViewModel.UiState.WeatherUiState.Success -> {
                        weatherAdapter.notifyDataSetChanged()
                    }
                    is WeatherViewModel.UiState.WeatherUiState.IsLoading -> {

                    }
                    else -> Unit
                }
            }
        }

    }

    private fun setupRecyclerView() = binding!!.weatherRecyclerView.apply {
        val instancePos = arguments.takeIf {
            it!!.containsKey(POS_ARG)
        }?.apply {
            binding!!.someText.text = (getInt(POS_ARG) + 1).toString()
        }
        weatherAdapter = WeatherAdapter(
            viewModel.uiState.value.forecast[instancePos!!.getInt(POS_ARG)]
        )
        adapter = weatherAdapter
        layoutManager = LinearLayoutManager(context)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}