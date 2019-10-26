package com.dm.testapp.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dm.testapp.data.ForecastModel
import com.dm.testapp.repo.RepoDatasource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class DetailViewModel: ViewModel(), KoinComponent {

    val repo: RepoDatasource by inject()
    val dataForecast = MutableLiveData<List<ForecastModel>>()
    val disposableBag = CompositeDisposable()
    init {
        Log.d("mylog","viewmodel")
    }

    fun feathForecast(city: String?){
      if(city != null) {
          val d = repo.getForecast(city)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe({ data -> dataForecast.postValue(data) },
                  { error -> Log.d("mylog", error.message) })
          disposableBag.add(d)
      }
    }

    override fun onCleared() {
        super.onCleared()
        disposableBag.clear()
    }
}