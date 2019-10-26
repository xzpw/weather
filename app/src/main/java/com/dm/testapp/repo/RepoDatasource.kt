package com.dm.testapp.repo

import com.dm.testapp.api.WeatherApi
import com.dm.testapp.data.ForecastModel
import com.dm.testapp.data.WeatherModel
import com.dm.testapp.data.current.convertWeatherModel
import com.dm.testapp.data.forecast.convertForecastModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RepoDatasource: KoinComponent {

    val api: WeatherApi by inject()



    fun getCurrent(): Observable<List<WeatherModel>>? {

        return Observable.just("london","kiev","dnipro","dubai","paris")
            .flatMap { getCurrentForCity(it) }
            .map { it }
            .toList()
            .toObservable()
    }


    fun getCurrentForCity(city: String):Observable<WeatherModel>{
        return api.getCurrentWeather(city,WeatherApi.APPID,WeatherApi.UNTIS).map { it.convertWeatherModel() }
    }

    fun getForecast(city: String):Observable<List<ForecastModel>>{
        return api.getForecast(city,WeatherApi.APPID,WeatherApi.UNTIS).map { it.convertForecastModel() }
    }
}