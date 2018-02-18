package com.androidassignment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidassignment.service.AnimalBreeds;

import java.util.ArrayList;

/**
 * Created by himadri on 18/2/18.
 */



public class AnimalsBreedsAdapter extends RecyclerView.Adapter<AnimalsBreedsAdapter.MyViewHolder> {

    private ArrayList<AnimalBreeds> dataList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public ImageView imageView;
        public RecyclerView rv_list;
        public LinearLayout ll_listItem;
        public AnimalsAdapter adapter;

        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            imageView = (ImageView) view.findViewById(R.id.iv_expand);
            rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
            ll_listItem=(LinearLayout)view.findViewById(R.id.ll_listItem);

        }
    }


    public AnimalsBreedsAdapter(ArrayList<AnimalBreeds> dataList) {
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final AnimalBreeds animal = dataList.get(holder.getAdapterPosition());
        holder.tv_name.setText(animal.getName());
        if(animal.getHasChild()){
            holder.imageView.setVisibility(View.VISIBLE);
            if(animal.isExpend()){
                holder.imageView.setImageResource(R.drawable.ic_expand_less_black_24dp);
                holder.rv_list.setVisibility(View.VISIBLE);
            }else{
                holder.imageView.setImageResource(R.drawable.ic_expand_more_black_24dp);
                holder.rv_list.setVisibility(View.GONE);
            }
            holder.adapter=new AnimalsAdapter(animal.getDogs());
            holder.rv_list.setAdapter(holder.adapter);
        }else{
            holder.imageView.setVisibility(View.GONE);
            holder.rv_list.setVisibility(View.GONE);
        }

        holder.ll_listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataList.get(holder.getAdapterPosition()).getHasChild()){
                    if(dataList.get(holder.getAdapterPosition()).isExpend()){
                        dataList.get(holder.getAdapterPosition()).setExpend(false);
                    }else {
                        dataList.get(holder.getAdapterPosition()).setExpend(true);
                    }
                }
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}