package com.example.carbid.retrofit;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService implements Serializable {
    private  Retrofit retrofit;
    final OkHttpClient okHttpClient = new OkHttpClient().newBuilder().readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(20,TimeUnit.SECONDS)
            .build();
    public RetrofitService() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.102:8050")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();
    }
    public Retrofit getRetrofit () {
        return retrofit;
    }
}
