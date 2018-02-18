package com.androidassignment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidassignment.service.AnimalBreeds;

import java.util.ArrayList;

/**
 * Created by himadri on 18/2/18.
 */



public class AnimalsAdapter extends RecyclerView.Adapter<AnimalsAdapter.MyViewHolder> {

    private ArrayList<String> dataList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;

        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view;
        }
    }


    public AnimalsAdapter(ArrayList<String> dataList) {
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_name.setText(dataList.get(holder.getAdapterPosition()));


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}