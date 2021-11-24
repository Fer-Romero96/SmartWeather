package com.example.smartweather

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartweather.databinding.FragmentWeatherHomeBinding
import com.example.smartweather.view.WeatherAdapter
import com.example.smartweather.viewmodel.WeatherViewModel

class WeatherHomeFragment : Fragment() {

    private var _binding : FragmentWeatherHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWeatherHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        createUI()

        return view
    }

    private fun createUI(){

        binding.recyclerWeather.layoutManager = LinearLayoutManager(context)

        binding.recyclerWeather.adapter = WeatherAdapter {

        }


        viewModel.getListWheater()

        viewModel.dayList.observe(viewLifecycleOwner, { listWeather ->
            (binding.recyclerWeather.adapter as WeatherAdapter).setData(listWeather)

        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}