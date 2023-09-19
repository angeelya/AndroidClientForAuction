package com.example.carbid.retrofit;

import com.example.carbid.model.MyBidRequest;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BidApi {
    @POST("/select/user/my/bid/user/id")
    Call<List<MyBidRequest>>findCarAndBid(@Header("Authorization")String token,@Body Map<String,String> map);
    @POST("/insert/bid/car/user")
    Call<Map<String,String>> insertBid(@Header("Authorization")String token,@Body Map<String,String> map);
}
