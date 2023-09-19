package com.example.carbid.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;

import java.io.Serializable;

public class CarImageHolder extends RecyclerView.ViewHolder implements Serializable {
    ImageView imgViewCarImg;
    public CarImageHolder(@NonNull View itemView) {
        super(itemView);
        imgViewCarImg = itemView.findViewById(R.id.imgViewCar);
    }
}
