package com.dm.testapp.data.current

import com.dm.testapp.data.WeatherModel

fun CurrentObj.convertWeatherModel():WeatherModel{

    return WeatherModel(
        this.name,
        this.main.temp,
        this.weather[0].description)
}