package com.example.carbid.retrofit;

import com.example.carbid.model.Destination;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface DestinationApi {
    @GET("/select/destination")
    Call<List<Destination>> getDestination ();
}
