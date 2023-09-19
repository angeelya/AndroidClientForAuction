package com.example.carbid.retrofit;

import com.example.carbid.model.TypeCar;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface TypeCarApi {
    @GET("/select/user/type/car")
    Call<List<TypeCar>> getTypeCar(@Header("Authorization")String token);
}
