package com.example.carbid.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;

import java.io.Serializable;

public class MyBidsHolder extends RecyclerView.ViewHolder implements Serializable {
    TextView carNameTxt;
    TextView priceBidTxt;
    ImageView carImageImg;
    CardView buttMyBidCard;
    public MyBidsHolder(@NonNull View itemView) {
        super(itemView);
        carImageImg = itemView.findViewById(R.id.carImage);
        carNameTxt = itemView.findViewById(R.id.carName);
        priceBidTxt = itemView.findViewById(R.id.priceBid);
        buttMyBidCard = itemView.findViewById(R.id.buttMyBid);
    }
}
