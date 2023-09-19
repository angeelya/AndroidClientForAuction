package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carbid.authSave.TokensSave;
import com.example.carbid.model.CarInfo;
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

public class InfoActivity extends AppCompatActivity implements Serializable {
    TextView modelBrandCarInfoTxt;
    TextView yearForCarInfoTxt;
    TextView vinCarInfoTxt;
    TextView typeForCarInfo;
    TextView colorCarInfoTxt;
    TextView mileageCarInfoTxt;
    TextView damageForCarInfoTxt;
    TextView documentForCarInfoTxt;
    TextView engineForCarInfoTxt;
    TextView cylindersForCarInfoTxt;
    TextView driveForCarInfoTxt;
    TextView typeBodyForCarInfoTxt;
    TextView transmissionForCarInfoTxt;
    TextView keysForCarInfoTxt;
    TextView fuelForCarInfoTxt;
TokensSave tokensSave = new TokensSave();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        modelBrandCarInfoTxt = findViewById(R.id.modelBrandCarInfo);
        yearForCarInfoTxt = findViewById(R.id.yearForCarInfo);
        vinCarInfoTxt = findViewById(R.id.vinCarInfo);
        typeForCarInfo = findViewById(R.id.typeForCarInfo);
        colorCarInfoTxt = findViewById(R.id.colorCarInfo);
        mileageCarInfoTxt = findViewById(R.id.mileageCarInfo);
        damageForCarInfoTxt= findViewById(R.id.damageForCarInfo);
        documentForCarInfoTxt = findViewById(R.id.documentForCarInfo);
        engineForCarInfoTxt = findViewById(R.id.engineForCarInfo);
        cylindersForCarInfoTxt = findViewById(R.id.cylindersForCarInfo);
        driveForCarInfoTxt = findViewById(R.id.driveForCarInfo);
        typeBodyForCarInfoTxt= findViewById(R.id.typeBodyForCarInfo);
        transmissionForCarInfoTxt= findViewById(R.id.transmissionForCarInfo);
        keysForCarInfoTxt= findViewById(R.id.keysForCarInfo);
        fuelForCarInfoTxt= findViewById(R.id.fuelForCarInfo);
        Intent intent = getIntent();
        String id_car =intent.getStringExtra("id_car");
        loadInfoCar(id_car);
    }

    private void loadInfoCar(String id_car) {
        Map<String,String> map = new HashMap<>();
        map.put("id_car",id_car);
        CarApi carApi = new RetrofitService().getRetrofit().create(CarApi.class);
        carApi.findInfoCar("Bearer "+tokensSave.getAccessToken(getApplicationContext()),map).enqueue(new Callback<CarInfo>() {
            @Override
            public void onResponse(Call<CarInfo> call, Response<CarInfo> response) {
                if(response.body()!=null)
                {
                  modelBrandCarInfoTxt.setText(response.body().getName());
                  yearForCarInfoTxt.setText(response.body().getYear());
                  vinCarInfoTxt.setText(response.body().getVin());
                  colorCarInfoTxt.setText(response.body().getColor());
                  typeForCarInfo.setText(response.body().getTypecar());
                  mileageCarInfoTxt.setText(String.valueOf(response.body().getMileage()));
                  damageForCarInfoTxt.setText(response.body().getDamage());
                  documentForCarInfoTxt.setText(response.body().getDocument());
                  engineForCarInfoTxt.setText(response.body().getEngine_type());
                  cylindersForCarInfoTxt.setText(String.valueOf(response.body().getCylinders()));
                  driveForCarInfoTxt.setText(response.body().getDrive());
                  typeBodyForCarInfoTxt.setText(response.body().getType_body());
                  transmissionForCarInfoTxt.setText(response.body().getTransmission());
                  keysForCarInfoTxt.setText(response.body().getKeys_car());
                  fuelForCarInfoTxt.setText(response.body().getFuel());
                  }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<CarInfo> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(InfoActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }
}