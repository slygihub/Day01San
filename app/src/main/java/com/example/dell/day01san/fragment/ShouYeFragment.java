package com.example.dell.day01san.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.day01san.R;
import com.example.dell.day01san.adapter.BanAdapter;
import com.example.dell.day01san.adapter.RecAdapter;
import com.example.dell.day01san.adapter.RecAdapter2;
import com.example.dell.day01san.bean.Basurl;
import com.example.dell.day01san.bean.Ben;
import com.example.dell.day01san.model.ShouYeModelimp;
import com.example.dell.day01san.preson.ShouYePersonimp;
import com.example.dell.day01san.view.ShouYeView;
import com.google.gson.Gson;
import com.youth.banner.Banner;

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
public class ShouYeFragment extends Fragment implements ShouYeView {


    private Banner ban;
    private RecyclerView rec1;
    private RecyclerView rec2;
    private ArrayList<Ben.ResultsBean> list1;
    private RecAdapter adapter;
    private ArrayList<Ben.ResultsBean> list2;
    private RecAdapter2 adapter2;

    public ShouYeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shou_ye, container, false);
        initView(view);
        ban.setImageLoader(new BanAdapter());
        ArrayList<String> list = new ArrayList<>();
        list.add("http://03.imgmini.eastday.com/mobile/20171109/20171109180455_d41d8cd98f00b204e9800998ecf8427e_5_mwpm_03200403.jpg");
        list.add("http://03.imgmini.eastday.com/mobile/20171109/20171109180455_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03200403.jpg");
        list.add("http://01.imgmini.eastday.com/mobile/20171109/20171109180324_8550569f222233a015e1cd7d70d2f2d4_2_mwpm_03200403.jpg");
        list.add("http://01.imgmini.eastday.com/mobile/20171109/20171109180324_0338994e5dcfa065d66d13cf701ccff3_1_mwpm_03200403.jpg");
        ban.setImages(list);
        ban.start();
        list1 = new ArrayList<>();
        adapter = new RecAdapter(list1, getContext());
        rec1.setAdapter(adapter);
        ShouYePersonimp personimp = new ShouYePersonimp(new ShouYeModelimp(), this);
        personimp.p();


        return view;
    }

    private void initView(View view) {
        ban = (Banner) view.findViewById(R.id.ban);
        rec1 = (RecyclerView) view.findViewById(R.id.rec1);
        rec2 = (RecyclerView) view.findViewById(R.id.rec2);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rec1.setLayoutManager(manager);
        LinearLayoutManager manager2 = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rec2.setLayoutManager(manager2);
        list2 = new ArrayList<>();
        adapter2 = new RecAdapter2(list2, getContext());
        rec2.setAdapter(adapter2);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Basurl.url)
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
                        list2.addAll(results);
                        adapter2.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void cheng(Ben ben) {
        List<Ben.ResultsBean> results = ben.getResults();
        list1.addAll(results);
        adapter.notifyDataSetChanged();

    }
}
