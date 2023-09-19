package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.carbid.adapter.ListCarForAddAuctionAdapter;
import com.example.carbid.app.App;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.model.CarAuctionAndFull;
import com.example.carbid.retrofit.CarApi;
import com.example.carbid.retrofit.RetrofitService;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCarForAddAuctionActivity extends AppCompatActivity implements Serializable {
   RecyclerView listCarForAuctionR;
   TokensSave tokensSave = new TokensSave();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_car_for_add_auction);
        listCarForAuctionR = findViewById(R.id.listCarForAuction);
        listCarForAuctionR.setLayoutManager(new LinearLayoutManager(this));
        loadCar();
    }

    private void loadCar() {
        CarApi carApi = new RetrofitService().getRetrofit().create(CarApi.class);
        carApi.findCarForAddAuction("Bearer "+tokensSave.getAccessToken(getApplicationContext())).enqueue(new Callback<List<CarAuctionAndFull>>() {
            @Override
            public void onResponse(Call<List<CarAuctionAndFull>> call, Response<List<CarAuctionAndFull>> response) {
               if(response.body()!=null)
                if(!response.body().isEmpty())
                {
                    listCars(response.body());
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                   Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                   toast.show();
               }
            }

            @Override
            public void onFailure(Call<List<CarAuctionAndFull>> call, Throwable t) {

            }
        });
    }

    private void listCars(List<CarAuctionAndFull> carAuctionAndFulls) {
        List<Integer> positions = ((App)getApplicationContext()).getPosition();
        if(!positions.isEmpty())
        {   for(int i =0; i<positions.size();i++)
              carAuctionAndFulls.remove(positions.get(i).intValue());
        }
        if(!carAuctionAndFulls.isEmpty()) {
            ((App)getApplicationContext()).setListCarForAddAuctionActivity(ListCarForAddAuctionActivity.this);
            ListCarForAddAuctionAdapter listCarForAddAuctionAdapter = new ListCarForAddAuctionAdapter(carAuctionAndFulls );
            listCarForAuctionR.setAdapter(listCarForAddAuctionAdapter);
        }
        else{
                Toast toast = Toast.makeText(getApplicationContext(),"Нет автомобилей",Toast.LENGTH_LONG);
                toast.show();

        }
    }
}