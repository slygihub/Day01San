package com.example.dell.day01san.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dell.day01san.R;
import com.example.dell.day01san.bean.Ben;

import java.util.ArrayList;

public class RecAdapter2 extends RecyclerView.Adapter<RecAdapter2.ViewHolder> {
    private ArrayList<Ben.ResultsBean> list2;
    private Context context;

    public RecAdapter2(ArrayList<Ben.ResultsBean> list2, Context context) {
        this.list2 = list2;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recitem2, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ben.ResultsBean bean = list2.get(position);
        Glide.with(context).load(bean.getUrl()).into(holder.url);
        Glide.with(context).load(bean.getUrl()).into(holder.url2);
        Glide.with(context).load(bean.getUrl()).into(holder.url3);
    }


    @Override
    public int getItemCount() {
        return list2.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView url;
        private final ImageView url2;
        private final ImageView url3;

        public ViewHolder(View itemView) {
            super(itemView);
            url = itemView.findViewById(R.id.url);
            url2 = itemView.findViewById(R.id.url2);
            url3 = itemView.findViewById(R.id.url3);
        }
    }
}
