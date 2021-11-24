package com.example.smartweather.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartweather.databinding.WeatherRowBinding
import com.example.smartweather.model.Daily

class WeatherAdapter (val dayClick : (Int) -> Unit) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

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

        holder.binding.DayClima.text = day.dt.toString()

        holder.itemView.setOnClickListener{ dayClick(day.dt.toInt()) }
    }

    override fun getItemCount() = daysList.size

}