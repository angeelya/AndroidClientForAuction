package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carbid.app.App;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.authSave.UserSave;
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

public class ChangeEmail extends AppCompatActivity implements Serializable {
     EditText NewEmailE;
     Button buttUpdateEmailB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
      NewEmailE = findViewById(R.id.NewEmail);
      buttUpdateEmailB=findViewById(R.id.buttUpdateEmail);
buttUpdateEmailB.setOnClickListener(v -> {
    updateEmail();
});
    }

    private void updateEmail() {
        UserSave userSave = new UserSave();
        TokensSave tokenSaver = new TokensSave();
        Map<String,String> map = new HashMap<>();
        map.put("id_user",userSave.getUser(getApplicationContext()));
        map.put("email",String.valueOf(NewEmailE.getText()));
        UsersApi usersApi = new RetrofitService().getRetrofit().create(UsersApi.class);
        usersApi.updateEmail("Bearer "+tokenSaver.getAccessToken(getApplicationContext()),map).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if(!response.body().isEmpty()&&response.body().get("message").equals("true")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Email успешно обновлен", Toast.LENGTH_LONG);
                    toast.show();
                    ((App)getApplicationContext()).getProfileFragment().emailUserTxt.setText(NewEmailE.getText());
                    finish();
                    ((App)getApplicationContext()).getChangeDataUserActivity().finish();}
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(ChangeEmail.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }
}