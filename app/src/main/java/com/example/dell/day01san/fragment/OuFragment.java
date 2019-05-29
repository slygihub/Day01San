package com.example.dell.day01san.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.day01san.R;
import com.example.dell.day01san.adapter.RecAdapter3;
import com.example.dell.day01san.bean.Basurl;
import com.example.dell.day01san.bean.Ben;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class OuFragment extends Fragment {


    private RecyclerView rec;
    private ArrayList<Ben.ResultsBean> list;
    private RecAdapter3 adapter3;

    public OuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ou, container, false);
        initView(view);
        list = new ArrayList<>();
        adapter3 = new RecAdapter3(list, getContext());
        rec.setAdapter(adapter3);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Basurl.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Basurl basurl = retrofit.create(Basurl.class);
        Observable<Ben> data = basurl.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Ben>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Ben ben) {
                        List<Ben.ResultsBean> results = ben.getResults();
                        list.addAll(results);
                        adapter3.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return view;
    }

    private void initView(View view) {
        rec = (RecyclerView) view.findViewById(R.id.rec);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rec.setLayoutManager(layoutManager);
    }
}
