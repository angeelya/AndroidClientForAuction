package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carbid.app.App;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.authSave.UserSave;
import com.example.carbid.model.AuctionReal;
import com.example.carbid.retrofit.AuctionApi;
import com.example.carbid.retrofit.BidApi;
import com.example.carbid.retrofit.QueryApi;
import com.example.carbid.retrofit.RetrofitService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuctionActivity extends AppCompatActivity implements Serializable {
    TextView nameCarOneAucTxt;
    TextView lotCarTxt;
    TextView bidCarActualTxt;
    TextView bidNameUserTxt;
    TextView bidCarOurTxt;
    TextView bidPlusTextTxt;
    ImageView buttPlsImgB;
    ImageView buttMinusImgB;
    CardView buttBidAuctionC;
    ImageView buttInfoImgB;
    ImageView listImgAuctionImg;
    String id_auction;
    UserSave userSave = new UserSave();
    TokensSave tokensSave = new TokensSave();
    Double bid;
    List<AuctionReal> auctionReals;
    private double bid_min;
   private CountDownTimer countDownTimer;
   private int count =0;
   TextView textTimerTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction);
        nameCarOneAucTxt = findViewById(R.id.nameCarOneAuc);
        lotCarTxt= findViewById(R.id.lotCar);
        bidCarActualTxt = findViewById(R.id.bidCarActual);
        bidNameUserTxt = findViewById(R.id.bidNameUser);
        bidCarOurTxt = findViewById(R.id.bidCarOur);
        bidPlusTextTxt = findViewById(R.id.bidPlusText);
        buttPlsImgB = findViewById(R.id.buttPls);
        buttMinusImgB = findViewById(R.id.buttMinus);
        buttBidAuctionC= findViewById(R.id.buttBidAuction);
        buttInfoImgB = findViewById(R.id.buttInfo);
        textTimerTxt = findViewById(R.id.textTimer);
        Intent in = getIntent();
        id_auction = in.getStringExtra("id_auction");
       // loadData();
        startAuction();
        buttMinusImgB.setOnClickListener(v -> {
            bid =Double.valueOf(bidPlusTextTxt.getText().toString());
            if(bid>Double.valueOf(bid_min))
            {
                bidPlusTextTxt.setText(String.valueOf(bid-10));
            }
        });
        buttPlsImgB.setOnClickListener(v -> {
            bid =Double.valueOf(bidPlusTextTxt.getText().toString());
            bidPlusTextTxt.setText(String.valueOf(bid+10));
        });
        buttBidAuctionC.setOnClickListener(v -> {
            if(!bidCarOurTxt.getText().toString().equals("У вас нет ставки")) {
                bidCarActualTxt.setText(bidPlusTextTxt.getText());
                bidNameUserTxt.setText("Alex");
                bidCarOurTxt.setText(bidPlusTextTxt.getText());
                // insertBid();
            }
                else{
                Toast toast;
                toast = Toast.makeText(getApplicationContext(),"У вас нет ставки",Toast.LENGTH_LONG);
                toast.show();
                }

        });
        buttInfoImgB.setOnClickListener(v -> {
            Intent intent = new Intent(this,InfoActivity.class);
            intent.putExtra("id_car",String.valueOf(lotCarTxt.getText()));
            startActivity(intent);
        });
    }

    private void startAuction() {
        //for(int i=0;i<auctionReals.size();i++)
        //{
        loadTimer();
          //  setAuctionRealsForActivity();
            countDownTimer.start();
            //resultAuctionOneCar();
       // }
        //((App)getApplicationContext()).getAuctionListActivity().loadAuction();
        //finish();
    }

    private void resultAuctionOneCar() {
        Map<String,String> map = new HashMap<>();
        map.put("id_car",String.valueOf(lotCarTxt.getText()));
        map.put("name",String.valueOf(bidNameUserTxt.getText()));
        QueryApi queryApi = new RetrofitService().getRetrofit().create(QueryApi.class);
        queryApi.insertQuery("Bearer "+tokensSave.getAccessToken(getApplicationContext()),map).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if(response.body()!=null) {
                    if (response.body().get("message").equals("true")) {
                       auctionReals.remove(auctionReals.size()-1);
                    }
                    else
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
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
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(AuctionActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }

    private void loadTimer() {
        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                count++;
                textTimerTxt.setText(String.valueOf(count));
            }

            @Override
            public void onFinish() {
                countDownTimer.start();
                if(count==60){
                    countDownTimer.cancel();
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Вы выиграли автомобиль",Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }

    private void insertBid() {
        Map<String,String> map = new HashMap<>();
        map.put("id_user",userSave.getUser(getApplicationContext()));
        map.put("id_car",String.valueOf(lotCarTxt.getText()));
        map.put("price",String.valueOf(bidPlusTextTxt.getText()));
        BidApi bidApi = new RetrofitService().getRetrofit().create(BidApi.class);
        bidApi.insertBid("Bearer "+tokensSave.getAccessToken(getApplicationContext()),map).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if(response.body()!=null&&response.body().get("message").equals("true")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Ставка поставлена", Toast.LENGTH_LONG);
                    toast.show();
                    bidCarActualTxt.setText(map.get("bid"));
                    bidNameUserTxt.setText(map.get("name"));
                    bidCarOurTxt.setText(map.get("bid"));
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
    }

    private void loadData() {
        Map<String,String> map = new HashMap<>();
        map.put("id_auction",id_auction);
        map.put("id_user",userSave.getUser(getApplicationContext()));
        AuctionApi auctionApi = new RetrofitService().getRetrofit().create(AuctionApi.class);
        auctionApi.getDataAuctionReal("Bearer " + tokensSave.getAccessToken(getApplicationContext()), map).enqueue(new Callback<List<AuctionReal>>() {
            @Override
            public void onResponse(Call<List<AuctionReal>> call, Response<List<AuctionReal>> response) {
               if(response.body()!=null){
                if  ( !response.body().isEmpty()){
                    setList(response.body());
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
             else{
                Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                toast.show();
            }
            }

            @Override
            public void onFailure(Call<List<AuctionReal>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(AuctionActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }

    private void setList(List<AuctionReal> body) {
     this.auctionReals = body;
    }

   public void setAuctionRealsForActivity()
   {
       AuctionReal auctionReal = auctionReals.get(auctionReals.size()-1);
       nameCarOneAucTxt.setText(auctionReal.getName());
       lotCarTxt.setText(String.valueOf(auctionReal.getId_car()));
       bidCarOurTxt.setText(auctionReal.getOurBid());
       byte[] image = Base64.getDecoder().decode(auctionReal.getImage());
       Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,image.length);
       listImgAuctionImg.setImageBitmap(bitmap);
       if(auctionReal.getOurBid().equals("Нет ставки"))
       {
           buttMinusImgB.setVisibility(View.GONE);
           buttPlsImgB.setVisibility(View.GONE);
           buttBidAuctionC.setVisibility(View.GONE);
       }
       bidCarActualTxt.setText(auctionReal.getMaxBid());
       bidNameUserTxt.setText(auctionReal.getName_user());
      bid_min = Long.valueOf(auctionReal.getMaxBid());
      bidPlusTextTxt.setText(String.valueOf(Long.valueOf(auctionReal.getMaxBid())+10));
   }
}