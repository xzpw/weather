package com.dm.testapp.data.forecast

import com.dm.testapp.data.ForecastModel

fun ForecastObj.convertForecastModel(): List<ForecastModel>{
    val listForecast: MutableList<ForecastModel> = mutableListOf()

    for (element in this.list){
        listForecast.add(ForecastModel(element.main.temp,element.weather[0].description,element.dt_txt))
    }
    return listForecast
}