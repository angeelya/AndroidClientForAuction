package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carbid.app.App;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.authSave.UserSave;
import com.example.carbid.retrofit.BidApi;
import com.example.carbid.retrofit.RetrofitService;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BidAdd extends AppCompatActivity {
   TextView textBidAddActTxt;
   CardView buttBidC;
   TextView bidPlusTextTxt;
   TextView bidPlusTextNowTxt;
   ImageView buttBidMinusImg;
   ImageView buttBidPlusImg;
   UserSave userSave = new UserSave();
   TokensSave tokensSave = new TokensSave();
   String bid_min;
   String name;
   String id_car;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_add);
        textBidAddActTxt = findViewById(R.id.textBidAddAct);
        buttBidC = findViewById(R.id.buttBid);
        bidPlusTextNowTxt =findViewById(R.id.bidPlusTextNow);
        buttBidPlusImg = findViewById(R.id.buttBidPlus);
        buttBidMinusImg = findViewById(R.id.buttBidMinus);
        bidPlusTextTxt = findViewById(R.id.bidPlusText);
        Intent intent = getIntent();
        bid_min =intent.getStringExtra("bid");
        name = intent.getStringExtra("name");
        id_car= intent.getStringExtra("id_car");
        textBidAddActTxt.setText(textBidAddActTxt.getText()+name);
        bidPlusTextTxt.setText(String.valueOf(Long.valueOf(bid_min)+10));
        bidPlusTextNowTxt.setText(bid_min);
        buttBidMinusImg.setOnClickListener(v -> {
            Long bid =Long.valueOf(bidPlusTextTxt.getText().toString());
            if(bid>Long.valueOf(bid_min))
            {
             bidPlusTextTxt.setText(String.valueOf(bid-10));
            }
        });
        buttBidPlusImg.setOnClickListener(view -> {
            Long bid =Long.valueOf(bidPlusTextTxt.getText().toString());
                    bidPlusTextTxt.setText(String.valueOf(bid+10));
        });
    buttBidC.setOnClickListener(v -> {
        Map<String,String> map = new HashMap<>();
        map.put("id_user",userSave.getUser(getApplicationContext()));
        map.put("id_car",id_car);
        map.put("price",String.valueOf(bidPlusTextTxt.getText()));
        BidApi bidApi = new RetrofitService().getRetrofit().create(BidApi.class);
        bidApi.insertBid("Bearer "+tokensSave.getAccessToken(getApplicationContext()),map).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if(response.body()!=null&&response.body().get("message").equals("true")){
                        Toast toast = Toast.makeText(getApplicationContext(), "Ставка поставлена", Toast.LENGTH_LONG);
                        toast.show();
                    ((App)getApplicationContext()).getCarActivity().bidForCarTxt.setText(bidPlusTextTxt.getText());
                        finish();
                      }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(BidAdd.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    });

    }
}
