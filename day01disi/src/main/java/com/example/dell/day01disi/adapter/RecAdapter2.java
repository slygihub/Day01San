package com.example.dell.day01disi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dell.day01disi.R;
import com.example.dell.day01disi.ben.Ben;

import java.util.ArrayList;

public class RecAdapter2 extends RecyclerView.Adapter<RecAdapter2.ViewHolder> {
    private ArrayList<Ben.ResultsBean>list;
    private Context context;

    public RecAdapter2(ArrayList<Ben.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recitem1, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ben.ResultsBean bean = list.get(position);
        Glide.with(context).load(bean.getUrl()).into(holder.url1);
        Glide.with(context).load(bean.getUrl()).into(holder.url2);
        Glide.with(context).load(bean.getUrl()).into(holder.url3);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView url1;
        private final ImageView url2;
        private final ImageView url3;

        public ViewHolder(View itemView) {
            super(itemView);
            url1 = itemView.findViewById(R.id.url);
            url2 = itemView.findViewById(R.id.url2);
            url3 = itemView.findViewById(R.id.url3);
        }
    }
}
