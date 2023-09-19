package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.carbid.adapter.BuyAdapter;
import com.example.carbid.adapter.QueryAdapter;
import com.example.carbid.adapter.UnapprovedAdapter;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.authSave.UserSave;
import com.example.carbid.model.Buy;
import com.example.carbid.model.QueryModel;
import com.example.carbid.model.Unapproved;
import com.example.carbid.retrofit.BuyApi;
import com.example.carbid.retrofit.QueryApi;
import com.example.carbid.retrofit.RetrofitService;
import com.example.carbid.retrofit.UnapprovedApi;

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

public class StatisticActivity extends AppCompatActivity implements Serializable {
      RecyclerView listQueryView;
      RecyclerView listBuyView;
      RecyclerView listUnapprovedView;
      ProgressBar progressBarQueryBar;
      ProgressBar progressBarBuyBar;
      ProgressBar progressBarUnapprovedBar;
      UserSave userSave = new UserSave();
      TokensSave tokensSave = new TokensSave();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        TabHost tabHostStat = findViewById(R.id.tabHostStatistic);
        tabHostStat.setup();
        TabHost.TabSpec tabSpec = tabHostStat.newTabSpec("query");
        tabSpec.setContent(R.id.query);
        tabSpec.setIndicator("Запросы");
        tabHostStat.addTab(tabSpec);
        tabSpec=tabHostStat.newTabSpec("buy");
        tabSpec.setContent(R.id.buy);
        tabSpec.setIndicator("Покупки");
        tabHostStat.addTab(tabSpec);
        tabSpec=tabHostStat.newTabSpec("unapproved");
        tabSpec.setContent(R.id.unapproved);
        tabSpec.setIndicator("Отказы");
        tabHostStat.addTab(tabSpec);
        tabHostStat.setCurrentTab(0);
        listQueryView = tabHostStat.findViewById(R.id.listQuery);
        listQueryView.setLayoutManager(new LinearLayoutManager(this));
        listBuyView = tabHostStat.findViewById(R.id.listBuy);
        listBuyView.setLayoutManager(new LinearLayoutManager(this));
        listUnapprovedView = tabHostStat.findViewById(R.id. listUnapproved);
        listUnapprovedView.setLayoutManager(new LinearLayoutManager(this));
        progressBarQueryBar = findViewById(R.id. progressBarQuery);
        progressBarBuyBar = findViewById(R.id. progressBarBuy);
        progressBarUnapprovedBar = findViewById(R.id.progressBarUnapproved);
        loadQuery();
        loadBuy();
        loadUnapproved();
    }

    private void loadQuery() {
        Map<String,String> request = new HashMap<>();
        request.put("id_user",userSave.getUser(getApplicationContext()));
        QueryApi queryApi = new RetrofitService().getRetrofit().create(QueryApi.class);
        queryApi.findQueryByUserId("Bearer " + tokensSave.getAccessToken(getApplicationContext()),request ).enqueue(new Callback<List<QueryModel>>() {
            @Override
            public void onResponse(Call<List<QueryModel>> call, Response<List<QueryModel>> response) {
                if(response.body()!=null){
                if  (!response.body().isEmpty()){
                    progressBarQueryBar.setVisibility(View.GONE);
                    listQuery(response.body());
                }
                else{
                //    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                  //  toast.show();
                }}
                else {
                    //     Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    //   toast.show();}
                }
            }

            @Override
            public void onFailure(Call<List<QueryModel>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(StatisticActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }

    private void listQuery(List<QueryModel> queryModelList) {
        QueryAdapter queryAdapter = new QueryAdapter(queryModelList);
        listQueryView.setAdapter(queryAdapter);
    }

    private void loadBuy() {
        Map<String,String> request = new HashMap<>();
        request.put("id_user",userSave.getUser(getApplicationContext()));
        BuyApi buyApi = new RetrofitService().getRetrofit().create(BuyApi.class);
        buyApi.selectBuy("Bearer " + tokensSave.getAccessToken(getApplicationContext()),request ).enqueue(new Callback<List<Buy>>() {
            @Override
            public void onResponse(Call<List<Buy>> call, Response<List<Buy>> response) {
                if  ( !response.body().isEmpty()){
                    progressBarBuyBar.setVisibility(View.GONE);
                    listBuy(response.body());
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<Buy>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(StatisticActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
    });
    }

    private void listBuy(List<Buy> buyList) {
        BuyAdapter buyAdapter = new BuyAdapter(buyList);
        listBuyView.setAdapter(buyAdapter);

    }

    private void  loadUnapproved() {
        Map<String,String> request = new HashMap<>();
        request.put("id_user",userSave.getUser(getApplicationContext()));
        UnapprovedApi unapprovedApi = new RetrofitService().getRetrofit().create(UnapprovedApi.class);
        unapprovedApi.selectUnapproved("Bearer " + tokensSave.getAccessToken(getApplicationContext()),request ).enqueue(new Callback<List<Unapproved>>() {
            @Override
            public void onResponse(Call<List<Unapproved>> call, Response<List<Unapproved>> response) {
                if  ( !response.body().isEmpty()){
                    progressBarUnapprovedBar.setVisibility(View.GONE);
                    listUnapproved(response.body());
                }
                else{
               //     Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                 //   toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<Unapproved>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(StatisticActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }

    private void listUnapproved(List<Unapproved> unapprovedList) {
        UnapprovedAdapter unapprovedAdapter = new UnapprovedAdapter(unapprovedList);
        listUnapprovedView.setAdapter(unapprovedAdapter);
    }
}