package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.carbid.adapter.FavoriteAdapter;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.authSave.UserSave;
import com.example.carbid.model.Favorite;
import com.example.carbid.retrofit.FavoriteApi;
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

public class FavoriteActivity extends AppCompatActivity implements Serializable {
     RecyclerView recyclerView;
     ProgressBar progressBarFavoriteBar;
     UserSave userSave=new UserSave();
     TokensSave tokensSave = new TokensSave();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        recyclerView = findViewById(R.id.listFavorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBarFavoriteBar = findViewById(R.id.progressBarFavorite);
        loadFavorite();
    }

    private void loadFavorite() {
        Map<String,String> request = new HashMap<>();
        request.put("id_user",userSave.getUser(getApplicationContext()));
        FavoriteApi favoriteApi = new RetrofitService().getRetrofit().create(FavoriteApi.class);
        favoriteApi.findFavoriteByUserId("Bearer "+tokensSave.getAccessToken(getApplicationContext()),request).enqueue(new Callback<List<Favorite>>() {
            @Override
            public void onResponse(Call<List<Favorite>> call, Response<List<Favorite>> response) {
                progressBarFavoriteBar.setVisibility(View.GONE);
                if(response.body()!=null){
                if(!response.body().isEmpty())
                {

                    listFavorite(response.body());
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"У вас нет избранных",Toast.LENGTH_LONG);
                    toast.show();
                }
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<Favorite>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(FavoriteActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }

    private void listFavorite(List<Favorite> favoriteList) {
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter (favoriteList);
        recyclerView.setAdapter(favoriteAdapter);
    }
}