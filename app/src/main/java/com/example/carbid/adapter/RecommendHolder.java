package com.example.carbid.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;
import com.example.carbid.SearchActivity;

import java.io.Serializable;

public class RecommendHolder extends RecyclerView.ViewHolder implements Serializable {
    TextView nameCarTxt;
    TextView bidCarTxt;
    TextView yearCarTxt;
    CardView carButtCard;
    ImageView imageCarItemImg;
    public RecommendHolder(@NonNull View itemView) {
        super(itemView);
        nameCarTxt = itemView.findViewById(R.id.nameCar);
        bidCarTxt = itemView.findViewById(R.id.bidCar);
        yearCarTxt = itemView.findViewById(R.id.yearCar);
        carButtCard = itemView.findViewById(R.id.carButt);
        imageCarItemImg = itemView.findViewById(R.id.imageCarItem);
    }
}
