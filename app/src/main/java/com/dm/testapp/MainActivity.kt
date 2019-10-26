package com.dm.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.dm.testapp.data.WeatherModel
import com.dm.testapp.repo.RepoDatasource
import com.dm.testapp.ui.NavigationRouter
import com.dm.testapp.ui.detail.DetailFragment
import com.dm.testapp.ui.list.ListFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(),NavigationRouter {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showList()
        setSupportActionBar(findViewById(R.id.toolbar))
        toolbar.title = "Weather"
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun showList() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ListFragment())
            .commit()
    }

    override fun showDetail(weatherModel: WeatherModel) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,DetailFragment.newInstance(weatherModel))
            .addToBackStack("")
            .commit()

    }
}
