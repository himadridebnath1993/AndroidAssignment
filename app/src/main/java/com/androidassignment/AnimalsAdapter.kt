package com.androidassignment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.*

/**
 * Created by himadri on 18/2/18.
 */


class AnimalsAdapter(private val dataList: ArrayList<String>) : RecyclerView.Adapter<AnimalsAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_name: TextView

        init {
            tv_name = view as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.simple_list_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv_name.text = dataList[holder.adapterPosition]


    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}