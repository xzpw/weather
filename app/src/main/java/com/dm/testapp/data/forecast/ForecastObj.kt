package com.dm.testapp.data.forecast

data class ForecastObj(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<X>,
    val message: Int
)