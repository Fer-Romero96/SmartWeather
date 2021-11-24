package com.example.smartweather

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.smartweather.databinding.FragmentWeatherDetailBinding
import java.text.SimpleDateFormat
import java.util.*

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

        if (isOnline()){
            createUI()
        } else {
            Toast.makeText(context, "Sin Conexión", Toast.LENGTH_LONG).show()
        }

        return binding.root
    }

    private fun createUI(){
        binding.dayTime.text = dateTime(args.daily.dt.toInt(), -6)
        binding.tempDay.text = "${args.daily.temp.day} °C"
        binding.tempMin.text = "${args.daily.temp.min} °C"
        binding.tempMax.text = "${args.daily.temp.max} °C"
        binding.tempNight.text = "${args.daily.temp.night} °C"
        binding.tempEven.text = "${args.daily.temp.eve} °C"
        binding.tempMorning.text = "${args.daily.temp.morn} °C"
        binding.main.text = args.daily.weather[0].main
        binding.tempDesc.text = args.daily.weather[0].description

    }

    fun isOnline(): Boolean {
        val connMgr = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

    fun dateTime(time: Int, zone: Int, format: String = "EEE, MMMM d K:mm a"): String {
        return try {
            val sdf = SimpleDateFormat(format)
            val netDate = Date((time.plus(zone)).toLong() * 1000)
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }
}