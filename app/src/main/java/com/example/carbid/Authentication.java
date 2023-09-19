package com.example.carbid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.carbid.authSave.AdminSave;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.authSave.UserSave;
import com.example.carbid.model.AuthenticationRequest;
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

public class Authentication extends AppCompatActivity implements Serializable {
    EditText loginTxt;
    EditText passwordTxt;
    Button buttLogin;
    TextView registerTxt;
    TextView infoTxt;
    UsersApi usersApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        loginTxt = findViewById(R.id.login);
        passwordTxt = findViewById(R.id.password);
         buttLogin = findViewById(R.id.buttLogin);
        registerTxt = findViewById(R.id.registerText);
        infoTxt = findViewById(R.id.info);
        usersApi = new RetrofitService().getRetrofit().create(UsersApi.class);

        buttLogin.setOnClickListener(v -> {
            authentication();
        });
        registerTxt.setOnClickListener(v -> {
            Intent intent = new Intent(Authentication.this,Registration.class);
            startActivity(intent);
            finish();
        });

    }

    private void authentication() {
        String name = loginTxt.getText().toString();
        String password = passwordTxt.getText().toString();
       if(!name.equals("")&&!password.equals(""))
       {
           AuthenticationRequest auth = new AuthenticationRequest();
           auth.setName(name);
           auth.setPassword(password);
         usersApi.authenticate(auth).enqueue(new Callback<Map<String, String>>() {
             @Override
             public void onResponse(@NonNull Call<Map<String, String>> call, @NonNull Response<Map<String, String>> response)  {
               if(!response.body().isEmpty())
                       validAuth(response.body());
               else{
                   Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                   toast.show();
               }
             }

             @Override
             public void onFailure(@NonNull Call<Map<String, String>> call, @NonNull Throwable t) {
                 if (t instanceof IOException) {
                     Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, "Ошибка", t);
                     Toast toast;
                     toast = Toast.makeText(getApplicationContext(), "Нет соединения с сервером", Toast.LENGTH_LONG);
                     toast.show();
                     infoTxt.setText("Не удалось войти");
                 }
             }
         });
       }
       else {
           infoTxt.setText("Не все поля заполнены");
       }
    }

    private void validAuth(Map<String, String> response) {
        if(response.get("message").equals("notFound")){
            infoTxt.setText("Пользователя с таким именем не существует");
        }
        if(response.get("message").equals("incorrect")){
            infoTxt.setText("Неверный логин или пароль");
        }
        else
        {
            if (response.get("role").equals("ADMINISTRATOR")) {
                AdminSave adminSave = new AdminSave();
                 adminSave.setModKey(getApplicationContext(),true);
            }
            UserSave userSave = new UserSave();
            userSave.setUser(getApplicationContext(),response.get("id_user"));
            TokensSave tokensSave = new TokensSave();
             tokensSave.setAccessToken(getApplicationContext(),response.get("access_token"));
            tokensSave.setRefreshToken(getApplicationContext(),response.get("refresh_token"));
            Intent intent = new Intent(Authentication.this,CarBidActivity.class);
            startActivity(intent);
            finish();
         }
        }

}
