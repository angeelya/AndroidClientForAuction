package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.carbid.adapter.LocationAuctionAdapter;
import com.example.carbid.app.App;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.model.LocationAuction;
import com.example.carbid.retrofit.LocationAuctionApi;
import com.example.carbid.retrofit.RetrofitService;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationForAddAuctionActivity extends AppCompatActivity implements Serializable {
RecyclerView listLocationR;
TokensSave tokensSave = new TokensSave();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_for_add_auction);
        listLocationR = findViewById(R.id.listLocation);
        listLocationR.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        loadLocation();
    }

    private void loadLocation() {
        LocationAuctionApi locationAuctionApi = new RetrofitService().getRetrofit().create(LocationAuctionApi.class);
        locationAuctionApi.findLocationAuction("Bearer "+tokensSave.getAccessToken(getApplicationContext())).enqueue(new Callback<List<LocationAuction>>() {
            @Override
            public void onResponse(Call<List<LocationAuction>> call, Response<List<LocationAuction>> response) {

                if(!response.body().isEmpty())
                {
                    listLocation(response.body());
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<LocationAuction>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(ViewCarActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }

    private void listLocation(List<LocationAuction> locationAuctions) {
        LocationAuctionAdapter locationAuctionAdapter = new LocationAuctionAdapter(locationAuctions,this);
        listLocationR.setAdapter(locationAuctionAdapter);
    }
}