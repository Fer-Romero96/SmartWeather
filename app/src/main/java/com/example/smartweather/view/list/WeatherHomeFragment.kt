package com.example.smartweather.view.list

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
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

        if (isOnline()){
            createUI()
        } else {
            Toast.makeText(context, "Sin Conexión", Toast.LENGTH_LONG).show()
        }

        return view
    }

    private fun createUI(){

        binding.recyclerWeather.layoutManager = LinearLayoutManager(context)

        viewModel.getListWheater()

        viewModel.dayList.observe(viewLifecycleOwner, { listWeather ->
            (binding.recyclerWeather.adapter as WeatherAdapter).setData(listWeather)

        })

        binding.recyclerWeather.adapter = WeatherAdapter {

            val action = WeatherHomeFragmentDirections.actionWeatherHomeFragmentToWeatherDetailFragment(it)

            view?.findNavController()?.navigate(action)

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun isOnline(): Boolean {
        val connMgr = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}