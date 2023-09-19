package com.example.carbid.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;

import java.io.Serializable;

public class LocationRegisterHolder extends RecyclerView.ViewHolder implements Serializable {
    TextView nameLocationTxt;
    CardView buttLocationItemC;
    public LocationRegisterHolder(@NonNull View itemView) {
        super(itemView);
        nameLocationTxt = itemView.findViewById(R.id.nameLocation);
        buttLocationItemC = itemView.findViewById(R.id.buttLocationItem);
    }
}
