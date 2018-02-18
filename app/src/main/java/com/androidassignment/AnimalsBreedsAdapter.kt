package com.androidassignment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.androidassignment.service.AnimalBreeds
import java.util.*

/**
 * Created by himadri on 18/2/18.
 */


class AnimalsBreedsAdapter(private val dataList: ArrayList<AnimalBreeds>) : RecyclerView.Adapter<AnimalsBreedsAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_name: TextView
        var imageView: ImageView
        var rv_list: RecyclerView
        var ll_listItem: LinearLayout
        var adapter: AnimalsAdapter? = null

        init {
            tv_name = view.findViewById<View>(R.id.tv_name) as TextView
            imageView = view.findViewById<View>(R.id.iv_expand) as ImageView
            rv_list = view.findViewById<View>(R.id.rv_list) as RecyclerView
            ll_listItem = view.findViewById<View>(R.id.ll_listItem) as LinearLayout

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val animal = dataList[holder.adapterPosition]
        holder.tv_name.text = animal.name
        if (animal.hasChild) {
            holder.imageView.visibility = View.VISIBLE
            if (animal.isExpend) {
                holder.imageView.setImageResource(R.drawable.ic_expand_less_black_24dp)
                holder.rv_list.visibility = View.VISIBLE
            } else {
                holder.imageView.setImageResource(R.drawable.ic_expand_more_black_24dp)
                holder.rv_list.visibility = View.GONE
            }
            holder.adapter = AnimalsAdapter(animal.dogs!!)
            holder.rv_list.adapter = holder.adapter
        } else {
            holder.imageView.visibility = View.GONE
            holder.rv_list.visibility = View.GONE
        }

        holder.ll_listItem.setOnClickListener {
            if (dataList[holder.adapterPosition].hasChild) {
                dataList[holder.adapterPosition].isExpend = !dataList[holder.adapterPosition].isExpend
            }
            notifyDataSetChanged()
        }


    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}