package com.example.dell.day01san.model;

import com.example.dell.day01san.bean.Basurl;
import com.example.dell.day01san.bean.Ben;
import com.example.dell.day01san.callback.ShouCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShouYeModelimp implements ShouYeModel {
    @Override
    public void m(final ShouCallBack shouCallBack) {
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
                        shouCallBack.cheng(ben);
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
