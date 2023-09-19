package com.example.carbid.retrofit;

import com.example.carbid.model.RecommendRequest;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RecommendApi {
    @POST("/select/user/recommend/car")
    Call<List<RecommendRequest>> getRecommend(@Header("Authorization")String token, @Body Map<String,String> map);
    @POST("/select/user/check/recommend")
    Call<Map<String,String>> checkRecommend(@Header("Authorization")String token, @Body Map<String,String> map);
}
