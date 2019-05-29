package com.example.dell.day01disi.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.day01disi.R;
import com.example.dell.day01disi.adapter.BanAdapter;
import com.example.dell.day01disi.adapter.RecAdapter1;
import com.example.dell.day01disi.adapter.RecAdapter2;
import com.example.dell.day01disi.ben.Basurl;
import com.example.dell.day01disi.ben.Ben;
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
public class DangFragment extends Fragment {


    private Banner ban;
    private RecyclerView rec1;
    private ArrayList<Ben.ResultsBean> list1;
    private RecAdapter2 adapter2;
    private RecyclerView rec2;
    private ArrayList<Ben.ResultsBean> list2;
    private RecAdapter1 adapter1;

    public DangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dang, container, false);
        initView(view);



        return view;
    }

    private void inioban() {
        ban.setImageLoader(new BanAdapter());
        final ArrayList<String> list = new ArrayList<>();
        list.add("http://03.imgmini.eastday.com/mobile/20171109/20171109180455_d41d8cd98f00b204e9800998ecf8427e_5_mwpm_03200403.jpg");
        list.add("http://03.imgmini.eastday.com/mobile/20171109/20171109180455_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03200403.jpg");
        list.add("http://01.imgmini.eastday.com/mobile/20171109/20171109180324_8550569f222233a015e1cd7d70d2f2d4_2_mwpm_03200403.jpg");
        list.add("http://01.imgmini.eastday.com/mobile/20171109/20171109180324_0338994e5dcfa065d66d13cf701ccff3_1_mwpm_03200403.jpg");
        ban.setImages(list);
        ban.start();
    }

    private void initView(View view) {
        ban = (Banner) view.findViewById(R.id.ban);
        rec1 = (RecyclerView) view.findViewById(R.id.rec1);
        rec2 = (RecyclerView) view.findViewById(R.id.rec2);
        inioban();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rec1.setLayoutManager(manager);
        list1 = new ArrayList<>();
        adapter2 = new RecAdapter2(list1, getContext());
        rec1.setAdapter(adapter2);


        LinearLayoutManager manager1 = new LinearLayoutManager(getContext());
        rec2.setLayoutManager(manager1);
        list2 = new ArrayList<>();
        adapter1 = new RecAdapter1(list2, getContext());
        rec2.setAdapter(adapter1);


        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
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
                        list1.addAll(results);
                        adapter2.notifyDataSetChanged();
                        list2.addAll(results);
                        adapter1.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }
}
