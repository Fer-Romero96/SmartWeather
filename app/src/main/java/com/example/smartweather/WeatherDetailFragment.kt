package com.example.smartweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.smartweather.databinding.FragmentWeatherDetailBinding

class WeatherDetailFragment : Fragment() {

    private var _binding : FragmentWeatherDetailBinding? = null
    private val binding get() = _binding!!
    private val args: WeatherDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherDetailBinding.inflate(inflater, container, false)

        createUI()

        return binding.root
    }

    private fun createUI(){
        binding.weatherText.text = args.daily.sunrise.toString()
    }
}