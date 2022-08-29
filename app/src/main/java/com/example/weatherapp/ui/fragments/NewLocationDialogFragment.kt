package com.example.weatherapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.DialogNewLocationFragmentBinding
import com.example.weatherapp.ui.viewmodels.WeatherViewModel

class NewLocationDialogFragment : DialogFragment(), View.OnClickListener {

    private var _binding: DialogNewLocationFragmentBinding? = null
    private val binding get() = _binding

    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL,
            com.google.android.material.R.style.Base_Theme_AppCompat_Dialog_MinWidth
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogNewLocationFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.apply {
            confirmButton.setOnClickListener(this@NewLocationDialogFragment)
            cancelButton.setOnClickListener(this@NewLocationDialogFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.cancel_button -> {
                dialog!!.dismiss()
            }
            R.id.confirm_button -> {
                viewModel.addLocation(binding!!.inputLocation.text.toString())
                dialog!!.dismiss()
            }
        }

    }
}