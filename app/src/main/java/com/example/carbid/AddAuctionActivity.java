package com.example.carbid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carbid.adapter.CarListAuctionAdapter;
import com.example.carbid.app.App;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.model.AuctionRequestAdd;
import com.example.carbid.model.CarAuctionAndFull;
import com.example.carbid.model.CarId;
import com.example.carbid.model.save.LocationAuctionSave;
import com.example.carbid.retrofit.AuctionApi;
import com.example.carbid.retrofit.RetrofitService;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAuctionActivity extends AppCompatActivity implements Serializable {
    public TextView locationAddAuctionTxt;
     EditText addNameAuctionE;
    EditText  increaseBidAuctionE;
    EditText dateAddAuctionE;
    EditText timeAddAuctionE;
    CardView buttAddLocationC;
    CardView buttAddCarAucC;
    Button buttAddAuctionB;
    CardView buttAddCarsId;
    RecyclerView listAuctionCarAddR;
    List<CarAuctionAndFull> carAuctionAndFullsAdd = new ArrayList<>();
    LocationAuctionSave auctionSave = new LocationAuctionSave();
    TokensSave tokensSave = new TokensSave();
    public List<CarId> carIdList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_auction);
        locationAddAuctionTxt = findViewById(R.id.locationAddAuction);
        addNameAuctionE = findViewById(R.id.addNameAuction);
        increaseBidAuctionE = findViewById(R.id.increaseBidAuction);
        dateAddAuctionE= findViewById(R.id.dateAddAuction);
        timeAddAuctionE = findViewById(R.id.timeAddAuction);
        buttAddLocationC = findViewById(R.id.buttAddLocation);
        buttAddCarAucC = findViewById(R.id.buttAddCarAuc);
        buttAddAuctionB= findViewById(R.id.buttAddAuction);
        listAuctionCarAddR = findViewById(R.id.listAuctionCarAdd);
        listAuctionCarAddR.setLayoutManager(new LinearLayoutManager(this));
        buttAddLocationC.setOnClickListener(v -> {

            Intent intent = new Intent(AddAuctionActivity.this,LocationForAddAuctionActivity.class);
            ((App)getApplicationContext()).setAddAuctionActivity(this);
            startActivity(intent);
        });
        buttAddCarAucC.setOnClickListener(v -> {
        Intent intent = new Intent(AddAuctionActivity.this,ListCarForAddAuctionActivity.class);
            ((App)getApplicationContext()).setAddAuctionActivity(this);
            startActivity(intent);
        });
        buttAddAuctionB.setOnClickListener(v -> {
            insertAuction();
        });
        loadCar();
    }

    private void insertAuction() {
        if (!addNameAuctionE.getText().toString().equals("")&&!increaseBidAuctionE.getText().toString().equals("")
        &&!dateAddAuctionE.getText().toString().equals("")&&!timeAddAuctionE.getText().toString().equals("")
        &&!locationAddAuctionTxt.getText().toString().equals("Месторасположение")&&!carIdList.isEmpty())
        {
            AuctionRequestAdd auctionRequestAdd = new AuctionRequestAdd();
            auctionRequestAdd.setName(String.valueOf(addNameAuctionE.getText()));
            auctionRequestAdd.setBid_increase(String.valueOf(increaseBidAuctionE.getText()));
            auctionRequestAdd.setId_location(Long.valueOf(auctionSave.getLocation(getApplicationContext())));
            auctionRequestAdd.setDate_auction(String.valueOf(dateAddAuctionE.getText())+" "+String.valueOf(timeAddAuctionE.getText()));
            auctionRequestAdd.setCarIdList(carIdList);
            AuctionApi auctionApi = new RetrofitService().getRetrofit().create(AuctionApi.class);
            auctionApi.insertAuction("Bearer "+tokensSave.getAccessToken(getApplicationContext()),auctionRequestAdd).enqueue(new Callback<Map<String, String>>() {
                @Override
                public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                    if(response.body()!=null){
                    if(response.body().get("message").equals("true")){
                        Toast toast = Toast.makeText(getApplicationContext(), "Аукцион добавлен", Toast.LENGTH_LONG);
                        toast.show();
                        clearAll();
                    }
                    else
                    {   Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
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
                        Logger.getLogger(AddAuctionActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                        Toast toast;
                        toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                        toast.show();}
                }
            });
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),"Не все поля заполнены",Toast.LENGTH_LONG);
            toast.show();
        }

    }

    private void clearAll() {
        ((App)getApplicationContext()).setCarAuctionAndFull(null);
        ((App)getApplicationContext()).setPosition(new ArrayList<>());
        ((App)getApplicationContext()).setPos(new HashMap<>());
        auctionSave.setLocation(getApplicationContext(),"");
        addNameAuctionE.setText("");
        increaseBidAuctionE.setText("");
        dateAddAuctionE.setText("");
        timeAddAuctionE.setText("");
        locationAddAuctionTxt.setText("Месторасположение");
        listAuctionCarAddR.setVisibility(View.GONE);
        carIdList.clear();
    }

    public void loadCar() {
        CarId carId = new CarId();
        if(((App)getApplicationContext()).getCarAuctionAndFull()!=null) {
            carId.setId_car(String.valueOf(((App) getApplicationContext()).getCarAuctionAndFull().getId_car()));
            carId.setName(((App) getApplicationContext()).getCarAuctionAndFull().getName());
            carId.setImage(((App) getApplicationContext()).getCarAuctionAndFull().getImage());
            carIdList.add(carId);
            listCar(carIdList);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearAll();
    }

    private void listCar(List<CarId> carIdList) {
        CarListAuctionAdapter carListAuctionAdapter = new CarListAuctionAdapter(carIdList);
        listAuctionCarAddR.setAdapter(carListAuctionAdapter);
        carListAuctionAdapter.notifyDataSetChanged();
      //  ((App)getApplicationContext()).getListCarForAddAuctionActivity().finish();
    }
}