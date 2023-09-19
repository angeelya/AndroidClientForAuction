package com.example.carbid.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;

import java.io.Serializable;

public class AuctionImgHolder extends RecyclerView.ViewHolder implements Serializable {
    ImageView imgViewCarActionRealImg;
    public AuctionImgHolder(@NonNull View itemView) {
        super(itemView);
        imgViewCarActionRealImg = itemView.findViewById(R.id.imgViewCarActionReal);
    }
}
