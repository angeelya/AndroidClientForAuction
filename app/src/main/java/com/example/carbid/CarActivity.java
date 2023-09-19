package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carbid.adapter.CarImageAdapter;
import com.example.carbid.app.App;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.authSave.UserSave;
import com.example.carbid.model.CarForCar;
import com.example.carbid.model.save.CountViewSave;
import com.example.carbid.retrofit.CarApi;
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

public class CarActivity extends AppCompatActivity {
     RecyclerView listImageC;
     ImageView buttFavI;
   public  TextView bidForCarTxt;
     Button buttAddBidB;
     TextView modelBrandCarTxt;
     TextView dateForCarAuctionTxt;
     TextView yearForCarTxt;
     TextView vinCarTxt;
     TextView typeForCarTxt;
     TextView colorCarTxt;
     TextView mileageCarTxt;
     TextView damageForCarTxt;
     TextView documentForCarTxt;
     TextView engineForCarTxt;
     TextView cylindersForCarTxt;
     TextView driveForCarTxt;
     TextView typeBodyForCarTxt;
     TextView transmissionForCarTxt;
     TextView keysForCarTxt;
     TextView fuelForCarTxt;
     String id_car;
     String activity;
     TokensSave tokensSave = new TokensSave();
     UserSave userSave = new UserSave();
     CountViewSave countViewSave= new CountViewSave();
     boolean fav;
    public String id_favorite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        listImageC = findViewById(R.id.listImage);
        listImageC.setLayoutManager(new LinearLayoutManager(this));
        buttFavI = findViewById(R.id.buttFav);
        bidForCarTxt = findViewById(R.id.bidForCar);
        buttAddBidB = findViewById(R.id.buttAddBid);
        modelBrandCarTxt = findViewById(R.id.modelBrandCar);
        dateForCarAuctionTxt = findViewById(R.id.dateForCarAuction);
        yearForCarTxt = findViewById(R.id.yearForCar);
        vinCarTxt = findViewById(R.id.vinCar);
        typeForCarTxt = findViewById(R.id.typeForCar);
        colorCarTxt = findViewById(R.id.colorCar);
        mileageCarTxt = findViewById(R.id.mileageCar);
        damageForCarTxt = findViewById(R.id.damageForCar);
        documentForCarTxt = findViewById(R.id.documentForCar);
        engineForCarTxt = findViewById(R.id.engineForCar);
        cylindersForCarTxt = findViewById(R.id.cylindersForCar);
        driveForCarTxt = findViewById(R.id.driveForCar);
        typeBodyForCarTxt = findViewById(R.id.typeBodyForCar);
        transmissionForCarTxt = findViewById(R.id.transmissionForCar);
        keysForCarTxt = findViewById(R.id.keysForCar);
        fuelForCarTxt = findViewById(R.id.fuelForCar);
        Intent intent = getIntent();
        id_car=intent.getStringExtra("id_car");
        activity = intent.getStringExtra("activity");
        if(!countViewSave.getCount(getApplicationContext()).equals(""))
        countViewSave.setCount(getApplicationContext(),String.valueOf(Long.valueOf(countViewSave.getCount(getApplicationContext()))+1));
        else  countViewSave.setCount(getApplicationContext(),String.valueOf(1));
        loadCar();
        checkFav();
        buttFavI.setOnClickListener(v -> {
            loadFavorite();
        });

    }

    private void checkFav() {
        Map<String,String> map = new HashMap<>();
        map.put("id_user",userSave.getUser(getApplicationContext()));
        map.put("id_car",id_car);
        FavoriteApi favoriteApi = new RetrofitService().getRetrofit().create(FavoriteApi.class);
        favoriteApi.checkFavorite("Bearer "+tokensSave.getAccessToken(getApplicationContext()),map).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if(response.body()!=null&&response.body().get("message").equals("true"))
                {
                        buttFavI.setImageResource(R.drawable.heart);
                        fav=true;
                        id_favorite=response.body().get("id_favorite");
                }

            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(CarActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }

    private void loadFavorite() {
        FavoriteApi favoriteApi = new RetrofitService().getRetrofit().create(FavoriteApi.class);
        if (!fav) {
            Map<String, String> map = new HashMap<>();
            map.put("id_user", userSave.getUser(getApplicationContext()));
            map.put("id_car", id_car);
            favoriteApi.insertFavorite("Bearer " + tokensSave.getAccessToken(getApplicationContext()), map).enqueue(new Callback<Map<String, String>>() {
                @Override
                public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                    if (response.body()!=null && response.body().get("message").equals("true")) {
                        fav = true;
                        buttFavI.setImageResource(R.drawable.heart);
                        id_favorite = response.body().get("id_favorite");
                        Toast toast = Toast.makeText(getApplicationContext(), "Добавлено", Toast.LENGTH_LONG);
                        toast.show();
                    } else {

                        Toast toast = Toast.makeText(getApplicationContext(), "Повторите попытку", Toast.LENGTH_LONG);
                        toast.show();

                    }
                }

                @Override
                public void onFailure(Call<Map<String, String>> call, Throwable t) {
                    if (t instanceof IOException) {
                        Logger.getLogger(CarActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                        Toast toast;
                        toast = Toast.makeText(getApplicationContext(), "Нет соединения с сервером", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });
        }
        else {
            Map<String, String> map = new HashMap<>();
            map.put("id_favorite", id_favorite);
            favoriteApi.deleteFavorite("Bearer " + tokensSave.getAccessToken(getApplicationContext()), map).enqueue(new Callback<Map<String, String>>() {
                @Override
                public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                    if(response.body()!=null){
                        modelBrandCarTxt.setText(response.body().get("message"));
                        if(response.body().get("message").equals("true")){
                            buttFavI.setImageResource(R.drawable.heart_empty);
                           fav=false;
                        }
                        else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Не удалось удалить", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                        toast.show();
                    }
                }

                @Override
                public void onFailure(Call<Map<String, String>> call, Throwable t) {
                    if( t instanceof IOException){
                        Logger.getLogger(CarActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                        Toast toast;
                        toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                        toast.show();}
                }
            });
        }
    }
    private void loadCar() {
        Map<String,String> map = new HashMap<>();
        map.put("id_car",id_car);
        map.put("id_user",userSave.getUser(getApplicationContext()));
        map.put("count_view",countViewSave.getCount(getApplicationContext()));
        CarApi carApi = new RetrofitService().getRetrofit().create(CarApi.class);
        carApi.findCarForCar("Bearer "+tokensSave.getAccessToken(getApplicationContext()),map).enqueue(new Callback<CarForCar>() {
            @Override
            public void onResponse(Call<CarForCar> call, Response<CarForCar> response) {
                if(response.body()!=null)
                {   if(Long.valueOf(countViewSave.getCount(getApplicationContext()))==10)
                    countViewSave.setCount(getApplicationContext(),"0");
                 modelBrandCarTxt.setText(response.body().getName());
                 dateForCarAuctionTxt.setText(response.body().getDate_auction());
                 yearForCarTxt.setText(response.body().getYear());
                 vinCarTxt.setText(response.body().getVin());
                 typeForCarTxt.setText(response.body().getTypecar());
                 colorCarTxt.setText(response.body().getColor());
                 mileageCarTxt.setText(String.valueOf(response.body().getMileage()));
                 damageForCarTxt.setText(response.body().getDamage());
                 documentForCarTxt.setText(response.body().getDocument());
                 engineForCarTxt.setText(response.body().getEngine_type());
                 cylindersForCarTxt.setText(String.valueOf(response.body().getCylinders()));
                 driveForCarTxt.setText(response.body().getDrive());
                 typeBodyForCarTxt.setText(response.body().getType_body());
                 transmissionForCarTxt.setText(response.body().getTransmission());
                 keysForCarTxt.setText(response.body().getKeys_car());
                 fuelForCarTxt.setText(response.body().getFuel());
                 bidForCarTxt.setText(response.body().getBid());
                 listImage(response.body().getImageList());
                    buttAddBidB.setOnClickListener(v -> {
                        Intent intent = new Intent(CarActivity.this,BidAdd.class);
                        intent.putExtra("bid",response.body().getBid());
                        intent.putExtra("name",response.body().getName());
                        intent.putExtra("id_car",String.valueOf(response.body().getId_car()));
                        ((App)getApplicationContext()).setCarActivity(CarActivity.this);
                        startActivity(intent);
                    });
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<CarForCar> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(CarActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });

    }

    private void listImage(List<String> imageList) {
        CarImageAdapter carImageAdapter = new CarImageAdapter(imageList);
        listImageC.setAdapter(carImageAdapter);
    }
}