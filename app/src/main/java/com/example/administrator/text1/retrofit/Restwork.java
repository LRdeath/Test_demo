package com.example.administrator.text1.retrofit;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;

/**
 * Created by Administrator on 2015/10/22.
 */
public interface Restwork {

    @GET("/101010100.html ")
    void mweather( Callback<Weather> callback);
}
