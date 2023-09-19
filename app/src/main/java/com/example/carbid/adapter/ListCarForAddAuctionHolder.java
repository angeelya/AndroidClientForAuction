package com.example.carbid.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;

import java.io.Serializable;

public class ListCarForAddAuctionHolder extends RecyclerView.ViewHolder implements Serializable {
    TextView nameCarAddAuctionTxt;
    TextView yearAddCarAuctionTxt;
    ImageView imageViewCarAuctionAddImg;
    CardView buttAddCarAucC;
    public ListCarForAddAuctionHolder(@NonNull View itemView) {
        super(itemView);
        nameCarAddAuctionTxt = itemView.findViewById(R.id.nameCarAddAuction);
        yearAddCarAuctionTxt = itemView.findViewById(R.id.yearAddCarAuction);
        imageViewCarAuctionAddImg = itemView.findViewById(R.id.imageViewCarAuctionAdd);
        buttAddCarAucC = itemView.findViewById(R.id.buttAddCarAuc);

    }
}
