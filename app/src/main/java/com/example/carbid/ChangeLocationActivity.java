package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.carbid.adapter.LocationUserAdapter;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.model.Destination;
import com.example.carbid.retrofit.DestinationApi;
import com.example.carbid.retrofit.RetrofitService;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeLocationActivity extends AppCompatActivity {
   RecyclerView listLocationUserR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_location);
        listLocationUserR = findViewById(R.id.listLocationUser);
        listLocationUserR.setLayoutManager(new LinearLayoutManager(this));
        loadLocation();
    }

    private void loadLocation() {
        DestinationApi destinationApi = new RetrofitService().getRetrofit().create(DestinationApi.class);
        destinationApi.getDestination().enqueue(new Callback<List<Destination>>() {
            @Override
            public void onResponse(Call<List<Destination>> call, Response<List<Destination>> response) {
               if(response.body()!=null){
                if  ( !response.body().isEmpty()){
                    listLocation(response.body());
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
               }
            }

            @Override
            public void onFailure(Call<List<Destination>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(ChangeLocationActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }

    private void listLocation(List<Destination> destinationList) {
        LocationUserAdapter locationUserAdapter = new LocationUserAdapter(destinationList,this);
        listLocationUserR.setAdapter(locationUserAdapter);
    }
}