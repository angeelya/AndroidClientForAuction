package com.example.carbid.retrofit;

import com.example.carbid.model.Buy;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BuyApi {
    @POST("/insert/user/query/yes")
    Call<Map<String,String>> insertBuy(@Header("Authorization")String token, @Body Map<String,String> map);
    @POST("/select/user/buy/user/id")
    Call<List<Buy>> selectBuy(@Header("Authorization")String token, @Body Map<String,String> map);
}
