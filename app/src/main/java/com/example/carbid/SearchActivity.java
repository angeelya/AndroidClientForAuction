package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.carbid.adapter.SearchAdapter;
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

public class SearchActivity extends AppCompatActivity implements Serializable {
    RecyclerView searchCarListR;
    TokensSave tokensSave = new TokensSave();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
     searchCarListR = findViewById(R.id.searchCarList);
     searchCarListR.setLayoutManager(new LinearLayoutManager(this));
     searchCarListR.setHasFixedSize(true);
     fetchCar("");
    }

    private void fetchCar(String key) {
        CarApi carApi = new RetrofitService().getRetrofit().create(CarApi.class);
        Map<String, String> request = new HashMap<>();
        request.put("key",key);
        carApi.searchCar("Bearer " + tokensSave.getAccessToken(getApplicationContext()),request).enqueue(new Callback<List<CarRequest>>() {
            @Override
            public void onResponse(Call<List<CarRequest>> call, Response<List<CarRequest>> response) {
                if  ( !response.body().isEmpty()){
                    listSearch(response.body());
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<CarRequest>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(SearchActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }

    private void listSearch(List<CarRequest> carRequestList) {
        SearchAdapter searchAdapter = new SearchAdapter(carRequestList);
        searchCarListR.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView=(SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchCar(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchCar(newText);
                return false;
            }
        });
        return true;
    }
}