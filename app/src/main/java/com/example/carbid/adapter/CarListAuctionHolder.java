package com.example.carbid.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;

import java.io.Serializable;

public class CarListAuctionHolder extends RecyclerView.ViewHolder implements Serializable {
     TextView nameCarAuctionActivityTxt;
     ImageView buttCarAuctionImg;
     ImageView buttDelCarAuctionBImg;
    public CarListAuctionHolder(@NonNull View itemView) {
        super(itemView);
        nameCarAuctionActivityTxt = itemView.findViewById(R.id.nameCarAuctionActivity);
        buttCarAuctionImg = itemView.findViewById(R.id.buttCarAuction);
        buttDelCarAuctionBImg= itemView.findViewById(R.id.buttDelCarAuction);
    }
}
