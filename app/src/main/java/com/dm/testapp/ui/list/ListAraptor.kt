package com.dm.testapp.ui.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dm.testapp.R
import com.dm.testapp.data.WeatherModel
import com.dm.testapp.ui.NavigationRouter
import kotlinx.android.synthetic.main.lsit_item.view.*

class ListAraptor(navigationRouter: NavigationRouter?): RecyclerView.Adapter<ListAraptor.ListHolder>() {

    val router: NavigationRouter? = navigationRouter
    lateinit var cities : List<WeatherModel>
    init {
        cities = listOf()
    }

    fun setData(data: List<WeatherModel>){
        cities = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.lsit_item,parent,false)
        return ListHolder(v,router)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.bind(cities[position])
    }



    class ListHolder(itemView: View, navigationRouter: NavigationRouter?) :RecyclerView.ViewHolder(itemView){
        val router = navigationRouter
        fun bind(c: WeatherModel){
            itemView.tvCityName.text = c.city
            itemView.tvDescription.text = c.description
            itemView.tvTemperature.text = c.temp.toString()+"°C"
            itemView.setOnClickListener { router!!.showDetail(c)
            Log.d("mylog","click")}
        }
    }
}