package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.carbid.adapter.AuctionListAdapter;
import com.example.carbid.app.App;
import com.example.carbid.model.AuctionListRequest;
import com.example.carbid.retrofit.AuctionApi;
import com.example.carbid.retrofit.RetrofitService;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuctionListActivity extends AppCompatActivity implements Serializable {
     RecyclerView recyclerView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction_list);
        progressBar = findViewById(R.id.progressBarListAuction);
        recyclerView = findViewById(R.id.listAuction);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ((App)getApplicationContext()).setAuctionListActivity(this);
        loadAuction();
    }

    public void loadAuction() {
        AuctionApi auctionApi = new RetrofitService().getRetrofit().create(AuctionApi.class);
        auctionApi.getAuctionList().enqueue(new Callback<List<AuctionListRequest>>() {
            @Override
            public void onResponse(Call<List<AuctionListRequest>> call, Response<List<AuctionListRequest>> response) {
                if  ( response.body()!=null){
                    progressBar.setVisibility(View.GONE);
                    listAuction(response.body());
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<AuctionListRequest>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(AuctionListActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }

    private void listAuction(List<AuctionListRequest> auctionListRequestList) {
        AuctionListAdapter auctionListAdapter = new AuctionListAdapter(auctionListRequestList);
        recyclerView.setAdapter(auctionListAdapter);
    }
}