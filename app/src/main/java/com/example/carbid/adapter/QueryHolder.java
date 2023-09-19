package com.example.carbid.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;

import java.io.Serializable;

public class QueryHolder extends RecyclerView.ViewHolder implements Serializable {
    TextView nameQueryCarTxt;
    TextView yearQueryCarTxt;
    TextView priceQueryCarTxt;
    Button buttYesB;
    Button buttNoB;
    ImageView imageQueryCarImg;
    public QueryHolder(@NonNull View itemView) {
        super(itemView);
        nameQueryCarTxt = itemView.findViewById(R.id.nameQueryCar);
        yearQueryCarTxt = itemView.findViewById(R.id.yearQueryCar);
        priceQueryCarTxt = itemView.findViewById(R.id.priceQueryCar);
        buttYesB = itemView.findViewById(R.id. buttYes);
        buttNoB = itemView.findViewById(R.id. buttNo);
        imageQueryCarImg = itemView.findViewById(R.id.imageQueryCar);
    }
}
