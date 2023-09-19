package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

public class PanelAdminActivity extends AppCompatActivity implements Serializable {
    CardView buttAddCarPanelC;
    CardView buttAddAuctionPanelC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_admin);
        buttAddAuctionPanelC = findViewById(R.id. buttAddAuctionPanel);
        buttAddCarPanelC = findViewById(R.id.buttAddCarPanel);
        buttAddCarPanelC.setOnClickListener(v -> {
            Intent intent = new Intent(this,AddCarActivity.class);
            startActivity(intent);
        });
        buttAddAuctionPanelC.setOnClickListener(v -> {
            Intent intent = new Intent(this,AddAuctionActivity.class);
            startActivity(intent);
        });
    }
}