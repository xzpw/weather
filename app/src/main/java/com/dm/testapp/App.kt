package com.dm.testapp

import android.app.Application
import com.dm.testapp.di.networkModule
import com.dm.testapp.di.repoModule
import com.dm.testapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            this@App
            modules(arrayListOf(viewModelModule, repoModule, networkModule))
            androidContext(applicationContext)
        }
    }
}