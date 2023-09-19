package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.carbid.adapter.ViewCarAdapter;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.authSave.UserSave;
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

public class ViewCarActivity extends AppCompatActivity implements Serializable {
  private RecyclerView recyclerView;
  ProgressBar progressBarViewCarBar;
  TokensSave tokensSave = new TokensSave();
  UserSave userSave = new UserSave();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_car);
        recyclerView = findViewById(R.id.listViewCar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBarViewCarBar = findViewById(R.id.progressBarViewCar);
        loadView();

    }

    private void loadView() {
        Map<String,String> request = new HashMap<>();
        request.put("id_user",userSave.getUser(getApplicationContext()));
        CarApi carApi = new RetrofitService().getRetrofit().create(CarApi.class);
        carApi.findCarByUserIdFromView("Bearer "+tokensSave.getAccessToken(getApplicationContext()),request).enqueue(new Callback<List<CarRequest>>() {
            @Override
            public void onResponse(Call<List<CarRequest>> call, Response<List<CarRequest>> response) {
                if(response.body()!=null)
                {   if(!response.body().isEmpty()){
                    progressBarViewCarBar.setVisibility(View.GONE);
                    listCarsView(response.body());}
                    else
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),"Нет просмотров",Toast.LENGTH_LONG);
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
            public void onFailure(Call<List<CarRequest>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(ViewCarActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }

    private void listCarsView(List<CarRequest> carRequestList) {
        ViewCarAdapter viewCarAdapter = new ViewCarAdapter(carRequestList);
        recyclerView.setAdapter(viewCarAdapter);
    }
}