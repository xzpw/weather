package com.dm.testapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dm.testapp.R
import com.dm.testapp.data.WeatherModel
import kotlinx.android.synthetic.main.detail_fragmen.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.lang.StringBuilder

class DetailFragment: Fragment() {

    val viewModel: DetailViewModel by viewModel()

    companion object{
        val ARG_DETAIL = "argument_detail"
        fun newInstance(weatherModel: WeatherModel):DetailFragment{
            val args = Bundle()
            args.putParcelable(ARG_DETAIL,weatherModel)
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragmen,container,false)
    }

    override fun onStart() {
        super.onStart()
       val city = arguments?.getParcelable<WeatherModel>(ARG_DETAIL)?.city
        tv_detail_temp.text = arguments?.getParcelable<WeatherModel>(ARG_DETAIL)?.temp.toString() + "°C"
        tv_detail_desc.text = arguments?.getParcelable<WeatherModel>(ARG_DETAIL)?.description
        viewModel.feathForecast(city)
        val string:StringBuilder = StringBuilder()
        viewModel.dataForecast.observe(this, Observer {

            for(data in it){
                val str = "${data.time}  ${data.temp} °C ${data.description} \n"
                string.append(str)
                Log.d("mylog",str)
            }
            Log.d("mylog",string.length.toString())
            tv_forecast.text = string
        })
    }
}