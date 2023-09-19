package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carbid.adapter.AuctionCarAdapter;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.model.CarRequest;
import com.example.carbid.retrofit.CarApi;
import com.example.carbid.retrofit.RetrofitService;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuctionCarActivity extends AppCompatActivity implements Serializable {
TextView nameAuctionCarTxt;
TextView dateAuctionCarTxt;
TextView locationAuctionCarTxt;
TextView countCarTxt;
CardView buttSortCard;
ProgressBar progressBarAuctionCarBar;
RecyclerView recyclerView;
TokensSave tokensSave= new TokensSave();
int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction_car);
        nameAuctionCarTxt = findViewById(R.id.nameAuctionCar);
        dateAuctionCarTxt = findViewById(R.id.dateAuctionCar);
        locationAuctionCarTxt = findViewById(R.id.locationAuctionCar);
        countCarTxt = findViewById(R.id.countCar);
        buttSortCard = findViewById(R.id.buttSort);
        recyclerView = findViewById(R.id.listCarAuction);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBarAuctionCarBar = findViewById(R.id.progressBarAuctionCar);
        loadAuction(getIntent());
        loadCars(getIntent(),"no_sort");
         Intent in = getIntent();
        buttSortCard.setOnClickListener(v -> {
           if(count==1){
            loadCars(in,"no_sort");
            count=0;
         }
           else
           {loadCars(in,"sort");
               count++;
              }
        });
    }



    private void loadAuction(Intent intent) {
        nameAuctionCarTxt.setText(intent.getStringExtra("name"));
        locationAuctionCarTxt.setText(intent.getStringExtra("location"));
        dateAuctionCarTxt.setText(intent.getStringExtra("date"));
    }
    private void loadCars(Intent intent, String sort) {
        Map<String,String> request = new HashMap<>();
        request.put("id_auction",intent.getStringExtra("id_auction"));
        request.put("sort",sort);
        CarApi carApi = new RetrofitService().getRetrofit().create(CarApi.class);

        carApi.findCarByAuctionId("Bearer "+tokensSave.getAccessToken(getApplicationContext()),request).enqueue(new Callback<List<CarRequest>>() {
            @Override
            public void onResponse(Call<List<CarRequest>> call, Response<List<CarRequest>> response) {
                if(response.body()!=null)
                {
                    progressBarAuctionCarBar.setVisibility(View.GONE);
                    countCarTxt.setText(String.valueOf(response.body().size()));
                    listCars(response.body());
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<CarRequest>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(AuctionCarActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });

    }

    private void listCars(List<CarRequest> listCarAuctionRequests) {
        AuctionCarAdapter auctionCarAdapter = new AuctionCarAdapter(listCarAuctionRequests);
        recyclerView.setAdapter(auctionCarAdapter);
    }


}