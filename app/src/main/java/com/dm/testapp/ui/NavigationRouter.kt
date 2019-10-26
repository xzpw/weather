package com.dm.testapp.ui

import com.dm.testapp.data.WeatherModel

interface NavigationRouter {

    fun showList()
    fun showDetail(weatherModel: WeatherModel)
}