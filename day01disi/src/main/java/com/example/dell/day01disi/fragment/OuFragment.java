package com.example.dell.day01disi.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.day01disi.R;
import com.example.dell.day01disi.adapter.RecAdapter;
import com.example.dell.day01disi.ben.Basurl;
import com.example.dell.day01disi.ben.Ben;

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
    private RecAdapter adapter;

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
        adapter = new RecAdapter(list, getContext());
        rec.setAdapter(adapter);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Basurl.url)
                .build();
        final Basurl basurl = retrofit.create(Basurl.class);
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
                        adapter.notifyDataSetChanged();
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
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rec.setLayoutManager(manager);
    }
}
