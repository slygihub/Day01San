package com.example.dell.day01disi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.day01disi.R;
import com.example.dell.day01disi.ben.Ben;

import java.util.ArrayList;

public class RecAdapter1 extends RecyclerView.Adapter<RecAdapter1.ViewHolder> {
    private ArrayList<Ben.ResultsBean>list2;
    private Context context;

    public RecAdapter1(ArrayList<Ben.ResultsBean> list2, Context context) {
        this.list2 = list2;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item2, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ben.ResultsBean bean = list2.get(position);
        holder.type.setText(bean.getType());
        Glide.with(context).load(bean.getUrl()).into(holder.url);
    }



    @Override
    public int getItemCount() {
        return list2.size();
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
