package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carbid.adapter.ImageCarAdapter;
import com.example.carbid.app.App;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.model.CarAddRequest;
import com.example.carbid.model.ImageCar;
import com.example.carbid.model.save.BrandSave;
import com.example.carbid.model.save.ModelSave;
import com.example.carbid.model.save.TypeCarSave;
import com.example.carbid.retrofit.CarApi;
import com.example.carbid.retrofit.RetrofitService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCarActivity extends AppCompatActivity implements Serializable {
  public TextView typeTransportCarAddTxt;
  public TextView brandCarAddTxt;
  public TextView modelCarAddTxt;
  public RecyclerView listImgCarAddR;
  CardView buttBrandAddC;
  CardView buttModelAddC;
  CardView buttTypeTransportAddC;
  EditText yearCarAddE;
  EditText minBidCarAddE;
  EditText reservePriceCarAddE;
  EditText vinCarAddE;
  EditText colorCarAddE;
  EditText mileageCarAddE;
  EditText documentCarAddE;
  EditText damageCarAddE;
  EditText engineCarAddE;
  EditText cylindersCarAddE;
  EditText driveCarAddE;
  EditText typeBodyCarAddE;
  EditText transmissionCarAddE;
  EditText keysCarAddE;
  EditText fuelCarAddE;
  Button buttAddCarB;
  Button buttAddImgB;
  private static final int GALLERY_REQUEST = 1;
  List<Bitmap> bitmaps = new ArrayList<>();
  TokensSave tokensSave = new TokensSave();
  BrandSave brandSave = new BrandSave();
  ModelSave modelSave = new ModelSave();
  TypeCarSave typeCarSave = new TypeCarSave();
    ImageCar imageCar = new ImageCar();
    public List<ImageCar> imageCarList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        typeTransportCarAddTxt = findViewById(R.id.typeTransportCarAdd);
        brandCarAddTxt = findViewById(R.id.brandCarAdd);
        modelCarAddTxt = findViewById(R.id.modelCarAdd);
        listImgCarAddR= findViewById(R.id.listImgCarAdd);
        listImgCarAddR.setLayoutManager(new LinearLayoutManager(this));
        buttBrandAddC = findViewById(R.id.buttBrandAdd);
        buttModelAddC = findViewById(R.id.buttModelAdd);
        buttTypeTransportAddC = findViewById(R.id.buttTypeTransportAdd);
        yearCarAddE = findViewById(R.id.yearCarAdd);
        minBidCarAddE = findViewById(R.id. minBidCarAdd);
        reservePriceCarAddE = findViewById(R.id.reservePriceCarAdd);
        vinCarAddE = findViewById(R.id.vinCarAdd);
        colorCarAddE = findViewById(R.id.colorCarAdd);
        mileageCarAddE = findViewById(R.id.mileageCarAdd);
        documentCarAddE =findViewById(R.id.documentCarAdd);
        damageCarAddE= findViewById(R.id.damageCarAdd);
        engineCarAddE = findViewById(R.id.engineCarAdd);
        cylindersCarAddE = findViewById(R.id.cylindersCarAdd);
        driveCarAddE = findViewById(R.id.driveCarAdd);
        typeBodyCarAddE = findViewById(R.id.typeBodyCarAdd);
        transmissionCarAddE= findViewById(R.id.transmissionCarAdd);
        keysCarAddE = findViewById(R.id.keysCarAdd);
        fuelCarAddE = findViewById(R.id.fuelCarAdd);
        buttAddCarB = findViewById(R.id.buttAddCar);
        buttAddImgB = findViewById(R.id.buttAddImg);

        Intent intent = new Intent(AddCarActivity.this,ItemCarAddActivity.class);
        buttBrandAddC.setOnClickListener(v -> {
            modelCarAddTxt.setText("Модель авто");
            modelSave.setID_Model(getApplicationContext(),"");
            intent.putExtra("buttCar","brand");
            ((App)getApplicationContext()).setAddCarActivity((this));
            startActivity(intent);
        });

        buttModelAddC.setOnClickListener(v -> {
            if(!brandSave.getID_Brand(getApplicationContext()).equals(""))
            {
                intent.putExtra("buttCar","model");
                ((App)getApplicationContext()).setAddCarActivity((this));
                startActivity(intent);
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(),"Выберите марку автомобиля",Toast.LENGTH_LONG);
                toast.show();
            }
        });
        buttTypeTransportAddC.setOnClickListener(v -> {
            intent.putExtra("buttCar","typeCar");
            ((App)getApplicationContext()).setAddCarActivity((this));
            startActivity(intent);
        });
        buttAddCarB.setOnClickListener(v -> {
            addCar();
        });
        buttAddImgB.setOnClickListener(v -> {
            listImgCarAddR.setVisibility(View.VISIBLE);
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Bitmap bitmap = null;
        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    String str= new File(selectedImage.getPath()).getName();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    bitmaps.add(bitmap);
                    listBitmap(bitmaps);
                    sendImage(bitmap,str);

                }
        }
    }

    private void listBitmap(List<Bitmap> bitmaps) {
        ImageCarAdapter imageCarAdapter =new ImageCarAdapter(bitmaps,this);
        listImgCarAddR.setAdapter(imageCarAdapter);
    }

    private void sendImage(Bitmap bitmap, String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,byteArrayOutputStream);
        byte[]  byteArray= byteArrayOutputStream.toByteArray();
        String imageBase64 = Base64.encodeToString(byteArray,Base64.NO_WRAP);
        this.imageCar.setImage64base(imageBase64);
        this.imageCar.setName_img(str+".jpg");
        this.imageCarList.add(imageCar);
    }
  private void addCar() {
      CarAddRequest carAddRequest = new CarAddRequest();
      if(!brandCarAddTxt.getText().toString().equals("Марка авто")&&!modelCarAddTxt.getText().toString().equals("Модель авто") &&!typeTransportCarAddTxt.getText().toString().equals("Тип транспорта")&&!yearCarAddE.getText().toString().equals("")
      &&!minBidCarAddE.getText().toString().equals("")&&!reservePriceCarAddE.getText().toString().equals("") &&!vinCarAddE.getText().toString().equals("")&&!colorCarAddE.getText().toString().equals("")&&!mileageCarAddE.getText().toString().equals("")
      &&!documentCarAddE.getText().toString().equals("")&&!damageCarAddE.getText().toString().equals("")&&!engineCarAddE.getText().toString().equals("")&&!cylindersCarAddE.getText().toString().equals("")&&!driveCarAddE.getText().toString().equals("")
      &&!typeBodyCarAddE.getText().toString().equals("")&&!transmissionCarAddE.getText().toString().equals("")&&!keysCarAddE.getText().toString().equals("")&&!fuelCarAddE.getText().toString().equals("")&&!imageCarList.isEmpty())
      {
      carAddRequest.setId_model(Long.valueOf(modelSave.getID_Model(getApplicationContext())));
      carAddRequest.setId_typecar(Long.valueOf(typeCarSave.getID_TypeCar(getApplicationContext())));
      carAddRequest.setYear(String.valueOf(yearCarAddE.getText()));
      carAddRequest.setVin(String.valueOf(vinCarAddE.getText()));
      carAddRequest.setColor(String.valueOf(colorCarAddE.getText()));
      carAddRequest.setFuel(String.valueOf(fuelCarAddE.getText()));
      carAddRequest.setMileage(Long.valueOf(mileageCarAddE.getText().toString()));
      carAddRequest.setDocument(String.valueOf(documentCarAddE.getText()));
      carAddRequest.setDamage(String.valueOf(damageCarAddE.getText()));
      carAddRequest.setEngine_type(String.valueOf(engineCarAddE.getText()));
      carAddRequest.setCylinders(Long.valueOf(cylindersCarAddE.getText().toString()));
      carAddRequest.setDrive(String.valueOf(driveCarAddE.getText()));
      carAddRequest.setType_body(String.valueOf(typeBodyCarAddE.getText()));
      carAddRequest.setTransmission(String.valueOf(transmissionCarAddE.getText()));
      carAddRequest.setKeys_car(String.valueOf(keysCarAddE.getText()));
      carAddRequest.setReserve_price(String.valueOf(reservePriceCarAddE.getText()));
      carAddRequest.setMin_bid(String.valueOf(minBidCarAddE.getText()));
      carAddRequest.setImages(imageCarList);
          CarApi carApi = new RetrofitService().getRetrofit().create(CarApi.class);
          carApi.insertCar("Bearer "+tokensSave.getAccessToken(getApplicationContext()),  carAddRequest).enqueue(new Callback<Map<String, String>>() {
              @Override
              public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                  if(response.body()!=null) {
                      if (response.body().get("message").equals("true")) {
                          Toast toast = Toast.makeText(getApplicationContext(), "Автомобиль добавлен", Toast.LENGTH_LONG);
                          toast.show();
                          clearAll();
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
                      Logger.getLogger(AddCarActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                      Toast toast;
                      toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                      toast.show();}
              }
          });
      }
      else
      {
          Toast toast = Toast.makeText(getApplicationContext(),"Не все поля заполнены",Toast.LENGTH_LONG);
          toast.show();
      }
  }

    private void clearAll() {
        brandSave.setID_Brand(getApplicationContext(),"");
        modelSave.setID_Model(getApplicationContext(),"");
        typeCarSave.setID_TypeCar(getApplicationContext(),"");
        listImgCarAddR.setVisibility(View.GONE);
        imageCarList.clear();
        bitmaps.clear();
        modelCarAddTxt.setText("Модель авто");
        brandCarAddTxt.setText("Марка авто");
        typeBodyCarAddE.setText("");
        typeTransportCarAddTxt.setText("Тип транспорта");
        yearCarAddE.setText("");
        vinCarAddE.setText("");
        colorCarAddE.setText("");
        mileageCarAddE.setText("");
        documentCarAddE.setText("");
        damageCarAddE.setText("");
        engineCarAddE.setText("");
        cylindersCarAddE.setText("");
        driveCarAddE.setText("");
        typeBodyCarAddE.setText("");
        transmissionCarAddE.setText("");
        keysCarAddE.setText("");
        fuelCarAddE.setText("");
        reservePriceCarAddE.setText("");
        minBidCarAddE.setText("");
    }
}