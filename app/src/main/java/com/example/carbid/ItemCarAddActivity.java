
package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.carbid.adapter.BrandAdapter;
import com.example.carbid.adapter.ModelAdapter;
import com.example.carbid.adapter.TypeCarAdapter;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.model.Brand;
import com.example.carbid.model.Model;
import com.example.carbid.model.TypeCar;
import com.example.carbid.model.save.BrandSave;
import com.example.carbid.retrofit.BrandApi;
import com.example.carbid.retrofit.ModelApi;
import com.example.carbid.retrofit.RetrofitService;
import com.example.carbid.retrofit.TypeCarApi;

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

public class ItemCarAddActivity extends AppCompatActivity implements Serializable {
RecyclerView listItemForAddCarR;

TokensSave tokensSave = new TokensSave();
BrandSave brandSave = new BrandSave();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_add_car);
        listItemForAddCarR = findViewById(R.id.listItemForAddCar);
        listItemForAddCarR.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        String in = intent.getStringExtra("buttCar");
        switch (in)
        {
            case "model": loadModel();break;
            case "brand" : loadBrand(); break;
            case "typeCar" : loadTypeCar(); break;
        }
    }

    private void loadTypeCar() {
        TypeCarApi typeCarApi = new RetrofitService().getRetrofit().create(TypeCarApi.class);
        typeCarApi.getTypeCar("Bearer "+tokensSave.getAccessToken(getApplicationContext())).enqueue(new Callback<List<TypeCar>>() {
            @Override
            public void onResponse(Call<List<TypeCar>> call, Response<List<TypeCar>> response) {
                if(!response.body().isEmpty())
                {
                    listTypeCar(response.body());
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<TypeCar>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(ItemCarAddActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }

    private void listTypeCar(List<TypeCar> typeCarList) {
        TypeCarAdapter typeCarAdapter = new TypeCarAdapter(typeCarList,this);
        listItemForAddCarR.setAdapter(typeCarAdapter);
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
        BrandAdapter brandAdapter = new BrandAdapter(brandList,this);
        listItemForAddCarR.setAdapter(brandAdapter);
    }

    private void loadModel() {
        Map<String,String> map = new HashMap<>();
        map.put("id_brand",brandSave.getID_Brand(getApplicationContext()));
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
        ModelAdapter modelAdapter = new ModelAdapter(modelList,this);
        listItemForAddCarR.setAdapter(modelAdapter);
    }
    }