package com.example.carbid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.carbid.app.App;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.authSave.UserSave;
import com.example.carbid.model.RegisterRequest;
import com.example.carbid.model.save.DestinationSave;
import com.example.carbid.retrofit.RetrofitService;
import com.example.carbid.retrofit.UsersApi;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity implements Serializable {
    EditText nameTxt;
    public TextView textButtLocationTxt;
    EditText registerEmailTxt;
    EditText registerPasswordTxt;
    EditText registerPasswordRepeatTxt;
    Button buttRegister;
    TextView backToAuth;
    TextView infoTxt;
    UsersApi usersApi;
    CardView AddLocationC;
    DestinationSave destinationSave = new DestinationSave();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        AddLocationC = findViewById(R.id.AddLocation);
        nameTxt = findViewById(R.id.registerLogin);
        registerEmailTxt = findViewById(R.id.registerEmail);
        registerPasswordTxt = findViewById(R.id.registerPassword);
        registerPasswordRepeatTxt = findViewById(R.id.registerPasswordRepeat);
        buttRegister = findViewById(R.id.buttRegister);
        backToAuth = findViewById(R.id.backToAuth);
        infoTxt = findViewById(R.id.infoRegister);
        textButtLocationTxt = findViewById(R.id.textButtLocation);
        usersApi= new RetrofitService().getRetrofit().create(UsersApi.class);
        buttRegister.setOnClickListener(v -> {
            register();
        });
        backToAuth.setOnClickListener(v -> {
            Intent intent = new Intent(Registration.this,Authentication.class);
            startActivity(intent);
            finish();
        });
        AddLocationC.setOnClickListener(v -> {
            ((App)getApplication()).setRegistration(this);
            Intent intent = new Intent(Registration.this,LocationRegister.class);
            startActivity(intent);
        });
    }

    public void register() {
        String name = nameTxt.getText().toString();
        String email = registerEmailTxt.getText().toString();
        String password = registerPasswordTxt.getText().toString();
        String passwordRepeat = registerPasswordRepeatTxt.getText().toString();
        if(!name.equals("")&&!email.equals("")&&!password.equals("")&&!passwordRepeat.equals("")&&!textButtLocationTxt.getText().toString().equals("Локация"))
        {
         if(password.equals(passwordRepeat)){

             RegisterRequest registerRequest = new RegisterRequest();
             registerRequest.setName(name);
             registerRequest.setEmail(email);
             registerRequest.setPassword(password);
             registerRequest.setId_destination(Long.valueOf(destinationSave.getLocation(getApplicationContext())));
             usersApi.register(registerRequest).enqueue(new Callback<Map<String, String>>() {
                 @Override
                 public void onResponse(@NonNull Call<Map<String, String>> call, @NonNull Response<Map<String, String>> response) {
                     if(response.body()!=null)
                         validRegister(response.body());
                     else{
                         Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                         toast.show();
                     }
                 }

                 @Override
                 public void onFailure(@NonNull Call<Map<String, String>> call, @NonNull Throwable t) {
                     if (t instanceof IOException) {
                         Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, "Ошибка", t);
                         Toast toast;
                         toast = Toast.makeText(getApplicationContext(), "Нет соединения с сервером", Toast.LENGTH_LONG);
                         toast.show();
                         infoTxt.setText("Не удалось зарегистрироваться");
                     }
                 }
             });
         }
         else{
             infoTxt.setText("Пароли не совпадают");
         }
        }
        else{
            infoTxt.setText("Не все поля заполнены");
        }
    }

    private void validRegister(Map<String, String> response) {
        if(!response.get("message").equals("userIsExists"))
        {
            UserSave userSave = new UserSave();
            userSave.setUser(getApplicationContext(),response.get("id_user"));
            TokensSave tokensSave = new TokensSave();
            tokensSave.setAccessToken(getApplicationContext(),response.get("access_token"));
            tokensSave.setRefreshToken(getApplicationContext(),response.get("refresh_token"));
            Intent intent = new Intent(Registration.this,CarBidActivity.class);
            startActivity(intent);
        }
        else
        {
        infoTxt.setText("Пользователь уже существует");
        }

    }
}