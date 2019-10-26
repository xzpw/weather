package com.dm.testapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dm.testapp.MainActivity
import com.dm.testapp.R
import com.dm.testapp.ui.NavigationRouter
import kotlinx.android.synthetic.main.list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment: Fragment() {

    val viewModel: ListViewModel by viewModel()
    lateinit var adapter :ListAraptor
    fun getRouter():NavigationRouter{
        return activity as NavigationRouter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.list_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).supportActionBar?.title = getString(R.string.app_name)
        viewModel.liveDataWeather.observe(this, Observer { adapter.setData(it) })
    }
    fun initView(view: View){
        adapter = ListAraptor(getRouter())
        rv_list.adapter = adapter
        rv_list.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        viewModel.fetchWeather()
    }
}