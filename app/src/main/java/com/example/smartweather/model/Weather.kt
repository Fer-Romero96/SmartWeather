package com.example.smartweather.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherResponse (
    val lat: Double,
    val lon: Double,
    val timezone: String,
    @SerializedName("timezone_offset")
    val timezoneOffset: Long,
    val current: Current,
    val daily: List<Daily>
) : Parcelable

@Parcelize
data class Current (
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    val pressure: Long,
    val humidity: Long,
    @SerializedName("dew_point")
    val dewPoint: Double,
    val uvi: Double,
    val clouds: Long,
    val visibility: Long,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("wind_deg")
    val windDeg: Long,
    @SerializedName("wind_gust")
    val windGust: Double,
    val weather: List<WeatherElement>
) : Parcelable

@Parcelize
data class WeatherElement (
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
) : Parcelable

@Parcelize
data class Daily (
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val moonrise: Long,
    val moonset: Long,
    val moonPhase: Double,
    val temp: Temp,
    val feelsLike: FeelsLike,
    val pressure: Long,
    val humidity: Long,
    val dewPoint: Double,
    val windSpeed: Double,
    val windDeg: Long,
    val windGust: Double,
    val weather: List<WeatherElement>,
    val clouds: Long,
    val pop: Double,
    val rain: Double? = null,
    val uvi: Double
) : Parcelable

@Parcelize
data class FeelsLike (
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
) : Parcelable

@Parcelize
data class Temp (
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
) : Parcelable

