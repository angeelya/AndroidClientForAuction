package com.example.carbid.retrofit;

import com.example.carbid.model.AuctionListRequest;
import com.example.carbid.model.AuctionReal;
import com.example.carbid.model.AuctionRequestAdd;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuctionApi {
    @GET("/select/auction/list")
    Call<List<AuctionListRequest>>getAuctionList();
    @POST("/insert/auction")
    Call<Map<String,String>> insertAuction(@Header("Authorization")String token, @Body AuctionRequestAdd auctionRequestAdd);
    @POST("/select/user/data/real/auction")
    Call<List<AuctionReal>>getDataAuctionReal(@Header("Authorization")String token, @Body Map<String,String> map);
}
