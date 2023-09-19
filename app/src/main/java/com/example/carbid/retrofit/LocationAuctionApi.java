package com.example.carbid.retrofit;


import com.example.carbid.model.LocationAuction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface LocationAuctionApi {
    @GET("/select/user/location/auction")
    Call<List<LocationAuction>> findLocationAuction(@Header("Authorization")String token);
}
