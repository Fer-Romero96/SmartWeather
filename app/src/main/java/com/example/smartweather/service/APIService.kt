package com.example.smartweather.service

import com.example.smartweather.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("onecall?lat=19.25&lon=-99.166672&exclude=minutely,hourly,alerts&units=metric&lang=es&APPID=52b82bca3705598e67b34207ff955c0b")
    fun getListWheater() : Call<WeatherResponse>

}