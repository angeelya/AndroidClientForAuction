package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;

import com.example.carbid.app.App;

import java.io.Serializable;

public class ChangeDataUserActivity extends AppCompatActivity{
     Button buttChangePasswordB;
     Button buttChangeEmailB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_data_user);
        buttChangePasswordB = findViewById(R.id.buttChangePassword);
        buttChangeEmailB = findViewById(R.id.buttChangeEmail);
        buttChangePasswordB.setOnClickListener(v -> {
         Intent intent = new Intent(this, ChangePassword.class);
         startActivity(intent);
        });
        buttChangeEmailB.setOnClickListener(v -> {
            ((App)getApplicationContext()).setChangeDataUserActivity(this);
            Intent intent = new Intent(this, ChangeEmail.class);
            startActivity(intent);
        });
    }
}