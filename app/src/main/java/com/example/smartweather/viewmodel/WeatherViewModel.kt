package com.example.smartweather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartweather.model.Daily
import com.example.smartweather.model.WeatherResponse
import com.example.smartweather.service.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherViewModel : ViewModel() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service : APIService = retrofit.create(APIService::class.java)

    val dayList = MutableLiveData<List<Daily>>()

    fun getListWheater(){

        val call = service.getListWheater()

        call.enqueue(object : Callback<WeatherResponse>{

            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                response.body()?.daily?.let { lista ->
                    dayList.postValue(lista)
                }
            }

            override fun onFailure(p0: Call<WeatherResponse>, p1: Throwable) {
                call.cancel()
            }

        })

    }
}