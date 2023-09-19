package com.example.carbid.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;

import java.io.Serializable;

public class BrandFullHolder extends RecyclerView.ViewHolder implements Serializable {
    TextView itemListTxt;
    CardView buttItemFullChooseC;
    public BrandFullHolder(@NonNull View itemView) {
        super(itemView);
        itemListTxt = itemView.findViewById(R.id.itemList);
        buttItemFullChooseC = itemView.findViewById(R.id.buttItemFullChoose);
    }
}
