package com.dm.testapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherModel (
    val city: String,
    val temp: Double,
    val description: String
):Parcelable