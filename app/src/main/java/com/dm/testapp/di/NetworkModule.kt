package com.dm.testapp.di

import com.dm.testapp.api.WeatherApi
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<Call.Factory> { okHttp() }
    single { retrofit(okHttp()) }
    single { apiProvider(get()) }

}

fun okHttp() = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
    .build()


fun retrofit(callFactory: Call.Factory) = Retrofit.Builder()
    .callFactory(callFactory)
    .baseUrl(WeatherApi.BASE_URL)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun apiProvider(retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)

