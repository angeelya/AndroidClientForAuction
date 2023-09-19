package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.carbid.adapter.FullSearchListAdapter;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.model.CarAuctionAndFull;
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

public class FullSearchListActivity extends AppCompatActivity implements Serializable {
    RecyclerView listCarFullR;
    TokensSave tokensSave = new TokensSave();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_search_list);
        listCarFullR = findViewById(R.id.listCarFull);
        listCarFullR.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        loadCar(intent.getStringExtra("id_model"),intent.getStringExtra("yearAfter"),intent.getStringExtra("yearBefore"));
    }

    private void loadCar(String id_model, String yearAfter, String yearBefore) {
        Map<String,String> map = new HashMap<>();
        map.put("id_model", id_model);
        map.put("yearBefore", yearBefore);
        map.put("yearAfter", yearAfter);
        CarApi carApi = new RetrofitService().getRetrofit().create(CarApi.class);
        carApi.findCarFull("Bearer "+tokensSave.getAccessToken(getApplicationContext()),map).enqueue(new Callback<List<CarAuctionAndFull>>() {
            @Override
            public void onResponse(Call<List<CarAuctionAndFull>> call, Response<List<CarAuctionAndFull>> response) {
               if(response.body()!=null){
                if(!response.body().isEmpty())
                {
                    listCar(response.body());
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Ничего не найдено",Toast.LENGTH_LONG);
                    toast.show();
                }
               }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<CarAuctionAndFull>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(FullSearchListActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }

    private void listCar(List<CarAuctionAndFull> carFullSearchList) {
        FullSearchListAdapter fullSearchListAdapter = new FullSearchListAdapter(carFullSearchList);
        listCarFullR.setAdapter(fullSearchListAdapter);
    }
}