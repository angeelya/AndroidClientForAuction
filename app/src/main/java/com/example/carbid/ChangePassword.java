package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class ChangePassword extends AppCompatActivity implements Serializable {
    EditText editTextOldPasswordE;
    EditText editTextNewPasswordE;
    EditText editTextNewRepeatPasswordE;
    Button buttChangePassB;
    TextView textInfoTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        editTextNewPasswordE = findViewById(R.id.editTextNewPassword);
        editTextOldPasswordE =findViewById(R.id.editTextOldPassword);
        editTextNewRepeatPasswordE = findViewById(R.id.editTextNewRepeatPassword);
        buttChangePassB =findViewById(R.id.buttChangePass);
        textInfoTxt = findViewById(R.id.textInfo);
        buttChangePassB.setOnClickListener(v -> {
            updatePassword();
        });
    }

    private void updatePassword() {
        UserSave userSave = new UserSave();
        TokensSave tokenSaver = new TokensSave();
        if(editTextNewPasswordE.getText().toString().equals(editTextNewRepeatPasswordE.getText().toString())){
            Map<String,String> map = new HashMap<>();
            map.put("id_user",userSave.getUser(getApplicationContext()));
            map.put("old_password",String.valueOf(editTextOldPasswordE.getText()));
            map.put("new_password",String.valueOf(editTextNewPasswordE.getText()));
            UsersApi usersApi = new RetrofitService().getRetrofit().create(UsersApi.class);
            usersApi.updatePassword("Bearer "+tokenSaver.getAccessToken(getApplicationContext()),map).enqueue(new Callback<Map<String, String>>() {
                @Override
                public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                    if(response.body()!=null){
                    Map<String,String> mapResponse = response.body();
                    if(mapResponse.get("message").equals("noOldPassword"))
                    {
                        textInfoTxt.setText("Неверный старый пароль");
                    }
                    else {
                        if(mapResponse.get("message").equals("true"))
                        {
                            finish();
                           ((App)getApplicationContext()).getChangeDataUserActivity().finish();
                        }
                    }
                }else
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                        toast.show();
                    }}

                @Override
                public void onFailure(Call<Map<String, String>> call, Throwable t) {
                    if( t instanceof IOException){
                        Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, "Ошибка", t);
                        Toast toast;
                        toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                        toast.show();}
                }
            });
        }
        else {
            textInfoTxt.setText("Пароли не совпадают");
        }
    }
}