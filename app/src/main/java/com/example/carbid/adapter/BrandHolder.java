package com.example.carbid.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;

import java.io.Serializable;

public class BrandHolder extends RecyclerView.ViewHolder implements Serializable {
    TextView textInfoCarAddTxt;
    CardView buttInfoForCarAddC;
    public BrandHolder(@NonNull View itemView) {
        super(itemView);
        textInfoCarAddTxt = itemView.findViewById(R.id.textInfoCarAdd);
        buttInfoForCarAddC = itemView.findViewById(R.id.buttInfoForCarAdd);
    }
}
