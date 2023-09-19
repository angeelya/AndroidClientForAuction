package com.example.carbid.retrofit;

import com.example.carbid.model.Unapproved;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UnapprovedApi {
    @POST("/insert/user/unapproved/query/no")
    Call<Map<String,String>> insertUnapproved(@Header("Authorization")String token, @Body Map<String,String> map);
    @POST("/select/user/unapproved/user/id")
    Call<List<Unapproved>> selectUnapproved (@Header("Authorization")String token, @Body Map<String,String> map);
}
