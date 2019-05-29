package com.example.dell.day01san.bean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Basurl {
    String url = "https://gank.io/api/";
    @GET("data/%E7%A6%8F%E5%88%A9/10/1")
    Observable<Ben>getData();
}
