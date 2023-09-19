package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.carbid.adapter.MyBidsAdapter;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.authSave.UserSave;
import com.example.carbid.model.MyBidRequest;
import com.example.carbid.retrofit.BidApi;
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

public class MyBidActivity extends AppCompatActivity implements Serializable {
   RecyclerView recyclerView;
   ProgressBar progressBarMyBidsBar;
   UserSave userSave = new UserSave();
   TokensSave tokensSave = new TokensSave();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bid);
        progressBarMyBidsBar = findViewById(R.id.progressBarMyBids);
        recyclerView = findViewById(R.id.listMyBid);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadBids();
    }

    private void loadBids() {
      Map<String,String> request = new HashMap<>();
      request.put("id_user",userSave.getUser(getApplicationContext()));
        BidApi bidApi = new RetrofitService().getRetrofit().create(BidApi.class);

        bidApi.findCarAndBid("Bearer "+tokensSave.getAccessToken(getApplicationContext()),request).enqueue(new Callback<List<MyBidRequest>>() {
            @Override
            public void onResponse(Call<List<MyBidRequest>> call, Response<List<MyBidRequest>> response) {
                progressBarMyBidsBar.setVisibility(View.GONE);
                if(response.body()!=null)
                if(!response.body().isEmpty())
                {

                    listMyBids(response.body());
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Вы не делали ставки",Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<MyBidRequest>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(MyBidActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }

    private void listMyBids(List<MyBidRequest> myBidsList) {
        MyBidsAdapter myBidsAdapter = new MyBidsAdapter(myBidsList);
        recyclerView.setAdapter(myBidsAdapter);
    }
}