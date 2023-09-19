package com.example.carbid.retrofit;

import com.example.carbid.model.Brand;
import com.example.carbid.model.TypeCar;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface BrandApi {
    @GET("/select/user/brand")
    Call<List<Brand>> getBrand(@Header("Authorization")String token);
}
