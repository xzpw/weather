package com.dm.testapp.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dm.testapp.data.WeatherModel
import com.dm.testapp.repo.RepoDatasource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class ListViewModel: ViewModel(), KoinComponent {

    val repo: RepoDatasource by inject()
    val liveDataWeather = MutableLiveData<List<WeatherModel>>()
    val liveRefresh = MutableLiveData<Boolean>()
    val liveError = MutableLiveData<Boolean>()
    val disposableBag = CompositeDisposable()

    init {
        liveRefresh.postValue(false)
    }

    fun fetchWeather(){
        liveRefresh.postValue(true)
        liveError.postValue(false)
       val d =   repo.getCurrent()
             ?.subscribeOn(Schedulers.io())
             ?.observeOn(AndroidSchedulers.mainThread())
             ?.subscribe({ data ->
                 liveDataWeather.postValue(data)
                 liveRefresh.postValue(false)

             },
                 { error-> liveRefresh.postValue(false)
                 liveError.postValue(true)})
        d?.let { disposableBag.add(it) }
    }

    override fun onCleared() {
        super.onCleared()
        disposableBag.clear()
    }
}