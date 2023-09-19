package com.example.carbid.retrofit;

import com.example.carbid.model.QueryModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface QueryApi {
    @POST("/select/user/query/user/id")
    Call<List<QueryModel>>findQueryByUserId(@Header("Authorization")String token, @Body Map<String,String> map);
    @HTTP(method = "DELETE", path = "/delete/user/query/id", hasBody = true)
    Call<Map<String,String>> deleteQuery(@Header("Authorization")String token,@Body Map<String,String> map);
    @POST("/insert/query")
    Call<Map<String,String>> insertQuery(@Header("Authorization")String token,@Body Map<String,String> map);
}
