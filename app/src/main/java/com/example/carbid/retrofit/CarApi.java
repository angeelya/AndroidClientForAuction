package com.example.carbid.retrofit;

import com.example.carbid.model.CarAddRequest;
import com.example.carbid.model.CarAuctionAndFull;
import com.example.carbid.model.CarForCar;
import com.example.carbid.model.CarInfo;
import com.example.carbid.model.CarRequest;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CarApi {
    @POST("/select/user/car/auction/id")
    Call<List<CarRequest>> findCarByAuctionId(@Header("Authorization")String token, @Body Map<String,String> map);
    @POST("/select/user/car/user/id/view")
    Call<List<CarRequest>> findCarByUserIdFromView(@Header("Authorization")String token, @Body Map<String,String> map);
    @POST("/select/user/cer/image/car/id")
    Call<Map<String,String>> findCarName(@Header("Authorization")String token, @Body Map<String,String> map);
    @POST("/select/user/buy/id/car")
    Call<Map<String,String>> selectCarId(@Header("Authorization")String token, @Body Map<String, String> map);
    @POST("/select/user/search/car")
    Call<List<CarRequest>> searchCar(@Header("Authorization")String token, @Body Map<String,String> map);
    @POST("/select/user/info/car")
    Call<CarInfo> findInfoCar(@Header("Authorization")String token, @Body Map<String,String> map);
    @GET("/select/user/car/add/auction")
    Call<List<CarAuctionAndFull>> findCarForAddAuction(@Header("Authorization")String token);
    @POST("/select/user/car/model/year")
    Call<List<CarAuctionAndFull>> findCarFull (@Header("Authorization")String token, @Body Map<String,String> map);
    @POST("/select/user/car/id/car/list/image")
    Call<CarForCar>findCarForCar (@Header("Authorization")String token, @Body Map<String, String> map);
    @POST("/insert/car/add")
    Call<Map<String,String>> insertCar(@Header("Authorization")String token, @Body CarAddRequest carAddRequest);
}
