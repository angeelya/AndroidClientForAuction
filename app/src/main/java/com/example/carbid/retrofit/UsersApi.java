package com.example.carbid.retrofit;

import com.example.carbid.model.AuthenticationRequest;
import com.example.carbid.model.RegisterRequest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UsersApi {
    @POST("/auth/authenticate")
    Call<Map<String,String>> authenticate(@Body AuthenticationRequest map);
    @POST("/auth/register")
    Call<Map<String,String>> register(@Body RegisterRequest registerRequest);
    @POST("/select/user/data")
    Call<Map<String,String>> getDataUser(@Header("Authorization")String token, @Body Map<String,String> map);
    @POST("/update/password")
    Call<Map<String,String>> updatePassword(@Header("Authorization")String token,@Body Map<String,String> map);
    @POST ("/update/email")
    Call<Map<String,String>> updateEmail(@Header("Authorization")String token,@Body Map<String,String> map);
    @POST ("/update/location")
    Call<Map<String,String>> updateLocation(@Header("Authorization")String token,@Body Map<String,String> map);

}
