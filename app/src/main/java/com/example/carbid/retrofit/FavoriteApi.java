package com.example.carbid.retrofit;

import com.example.carbid.model.Favorite;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FavoriteApi {
    @POST("/select/user/favorite/user/id")
    Call<List<Favorite>> findFavoriteByUserId (@Header("Authorization")String token, @Body Map<String,String> map);
    @POST("/insert/favorite/user/id/car")
    Call<Map<String,String>> insertFavorite(@Header("Authorization")String token, @Body Map<String,String> map);
    @POST("/select/user/favorite/user/id/car")
    Call<Map<String,String>> checkFavorite (@Header("Authorization")String token, @Body Map<String,String> map);
    @HTTP(method = "DELETE", path = "/delete/user/favorite/id", hasBody = true)
    Call<Map<String,String>> deleteFavorite(@Header("Authorization")String token,@Body Map<String,String> map);
}
