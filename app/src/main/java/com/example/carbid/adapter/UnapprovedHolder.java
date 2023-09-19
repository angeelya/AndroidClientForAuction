package com.example.carbid.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;

import java.io.Serializable;

public class UnapprovedHolder extends RecyclerView.ViewHolder implements Serializable {
    TextView nameCarTxt;
    TextView bidCarTxt;
    TextView yearCarTxt;
    CardView carButtCard;
    ImageView imageCarItemImg;
    public UnapprovedHolder(@NonNull View itemView) {
        super(itemView);
        nameCarTxt = itemView.findViewById(R.id.nameCar);
        bidCarTxt = itemView.findViewById(R.id.bidCar);
        yearCarTxt = itemView.findViewById(R.id.yearCar);
        carButtCard = itemView.findViewById(R.id.carButt);
        imageCarItemImg = itemView.findViewById(R.id.imageCarItem);
    }
}
