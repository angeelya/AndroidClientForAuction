package com.example.carbid.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;

import java.io.Serializable;

public class BuyHolder extends RecyclerView.ViewHolder implements Serializable {
    TextView nameBuyCarTxt;
    TextView yearBuyCarTxt;
    TextView  bidBuyCarTxt;
    TextView priceBuyCarTxt;
    ImageView imageBuyCarImg;
    public BuyHolder(@NonNull View itemView) {
        super(itemView);
        nameBuyCarTxt = itemView.findViewById(R.id. nameBuyCar);
        yearBuyCarTxt = itemView.findViewById(R.id.yearBuyCar);
        bidBuyCarTxt = itemView.findViewById(R.id.bidBuyCar);
        priceBuyCarTxt = itemView.findViewById(R.id.priceBuyCar);
        imageBuyCarImg = itemView.findViewById(R.id.imageBuyCar);
    }
}
