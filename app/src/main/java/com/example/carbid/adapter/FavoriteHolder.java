package com.example.carbid.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;

import java.io.Serializable;

public class FavoriteHolder extends RecyclerView.ViewHolder implements Serializable {
     TextView nameCarFavTxt;
     ImageView buttDelFavImg;
     ImageView buttCarFavImg;
    public FavoriteHolder(@NonNull View itemView) {
        super(itemView);
        nameCarFavTxt = itemView.findViewById(R.id.nameCarFav);
        buttCarFavImg = itemView.findViewById(R.id.buttCarFav);
        buttDelFavImg = itemView.findViewById(R.id.buttDelFav);
    }
}
