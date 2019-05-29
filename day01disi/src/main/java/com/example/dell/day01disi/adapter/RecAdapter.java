package com.example.dell.day01disi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.day01disi.R;
import com.example.dell.day01disi.ben.Ben;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {
    private ArrayList<Ben.ResultsBean>list;
    private Context context;

    public RecAdapter(ArrayList<Ben.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ben.ResultsBean bean = list.get(position);
        holder.type.setText(bean.getType());
        Glide.with(context).load(bean.getUrl()).into(holder.url);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView url;
        private final TextView type;

        public ViewHolder(View itemView) {
            super(itemView);
            url = itemView.findViewById(R.id.url);
            type = itemView.findViewById(R.id.type);
        }
    }
}
