package com.example.carbid.retrofit;

import com.example.carbid.model.Model;
import com.example.carbid.model.TypeCar;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ModelApi {
    @POST("/select/user/model/brand")
    Call<List<Model>> getModel(@Header("Authorization")String token, @Body Map<String,String> map);
}
