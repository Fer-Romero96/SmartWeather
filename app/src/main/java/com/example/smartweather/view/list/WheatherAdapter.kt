package com.example.smartweather.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartweather.R
import com.example.smartweather.databinding.WeatherRowBinding
import com.example.smartweather.model.Daily
import com.example.smartweather.model.WeatherResponse
import java.text.SimpleDateFormat
import java.util.*

class WeatherAdapter (val dayClick : (Daily) -> Unit) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    var daysList : List<Daily> = emptyList<Daily>()

    fun setData( list: List<Daily>){
        daysList = list
        notifyDataSetChanged()
    }

    inner class WeatherViewHolder(val binding: WeatherRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = WeatherRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val day = daysList[position]

        holder.binding.DayClima.text = dateTime(day.dt.toInt(),-6)
        holder.binding.DescClima.text = day.weather[0].main
        holder.binding.DescClima.text = day.weather[0].description

        Glide.with(holder.itemView.context)
            .load("https://openweathermap.org/img/wn/${day.weather[0].icon}@2x.png")
            .override(200,200)
            .centerCrop()
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.binding.iconWeath)

        holder.itemView.setOnClickListener{ dayClick(day) }
    }


    override fun getItemCount() = daysList.size

    fun dateTime(time: Int, zone: Int, format: String = "EEE, MMMM d"): String {

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