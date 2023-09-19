package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.carbid.adapter.BrandFullAdapter;
import com.example.carbid.adapter.ModelFullAdapter;
import com.example.carbid.adapter.YearFullAdapter;
import com.example.carbid.app.App;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.model.Brand;
import com.example.carbid.model.Model;
import com.example.carbid.model.save.BrandFullSave;
import com.example.carbid.retrofit.BrandApi;
import com.example.carbid.retrofit.ModelApi;
import com.example.carbid.retrofit.RetrofitService;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullItemSearchActivity extends AppCompatActivity implements Serializable {
    RecyclerView listItemSearchR;
    TokensSave tokensSave = new TokensSave();
    String in;
    BrandFullSave brandFullSave = new BrandFullSave();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_item_search);
        listItemSearchR = findViewById(R.id.listItemSearch);
        listItemSearchR.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        in=intent.getStringExtra("butt");
        switch (in)
        {
            case "brand": loadBrand(); break;
            case "model": loadModel(); break;
            case "yearBefore": loadYear("before"); break;
            case "yearAfter": loadYear("after"); break;
        }
    }

    private void loadModel() {
        Map<String,String > map = new HashMap<>();
        map.put("id_brand",brandFullSave.getID_BrandFull(getApplicationContext()));
        brandFullSave.setID_BrandFull(getApplicationContext(),"");
        ModelApi modelApi = new RetrofitService().getRetrofit().create(ModelApi.class);
        modelApi.getModel("Bearer "+tokensSave.getAccessToken(getApplicationContext()),map).enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if(!response.body().isEmpty())
                {
                    listModel(response.body());
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(ItemCarAddActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }

    private void listModel(List<Model> modelList) {
        ((App)getApplicationContext()).setFullItemSearchActivity(this);
        ModelFullAdapter modelFullAdapter = new ModelFullAdapter(modelList);
        listItemSearchR.setAdapter(modelFullAdapter);
    }

    private void loadBrand() {
        BrandApi brandApi = new RetrofitService().getRetrofit().create(BrandApi.class);
        brandApi.getBrand("Bearer "+tokensSave.getAccessToken(getApplicationContext())).enqueue(new Callback<List<Brand>>() {
            @Override
            public void onResponse(Call<List<Brand>> call, Response<List<Brand>> response) {
                if(!response.body().isEmpty())
                {
                    listBrand(response.body());
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<Brand>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(ItemCarAddActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }

    private void listBrand(List<Brand> brandList) {
        ((App)getApplicationContext()).setFullItemSearchActivity(this);
        BrandFullAdapter brandFullAdapter = new BrandFullAdapter(brandList);
        listItemSearchR.setAdapter(brandFullAdapter);
    }

    private void loadYear(String ifYear) {
        List<Integer> years = new ArrayList<Integer>();
        int endYear = Calendar.getInstance().get(Calendar.YEAR);
        for(int year = 1990;year <= endYear; year++){
            years.add(year);
        }
        ((App)getApplicationContext()).setFullItemSearchActivity(this);
        YearFullAdapter yearFullAdapter = new YearFullAdapter(years,ifYear);
        listItemSearchR.setAdapter(yearFullAdapter);
    }
}