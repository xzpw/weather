package com.dm.testapp.api

import com.dm.testapp.data.current.CurrentObj
import com.dm.testapp.data.forecast.ForecastObj
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    //http://api.openweathermap.org/data/2.5/weather?q=dnipro&APPID=e9bad0a8d3387d7b513fffcd5b3b55fe


    @GET("weather")
    fun getCurrentWeather(@Query("q") q: String,
                          @Query("APPID") appid: String,
                          @Query("units")units: String):Observable<CurrentObj>

    //http://api.openweathermap.org/data/2.5/forecast?q=kiev&APPID=e9bad0a8d3387d7b513fffcd5b3b55fe
    @GET("forecast")
    fun getForecast(@Query("q") q: String,
                    @Query("APPID") appid: String,
                    @Query("units")units: String):Observable<ForecastObj>

    companion object{
        val BASE_URL = "http://api.openweathermap.org/data/2.5/"
        val APPID = "e9bad0a8d3387d7b513fffcd5b3b55fe"
        val UNTIS = "metric"
    }
}