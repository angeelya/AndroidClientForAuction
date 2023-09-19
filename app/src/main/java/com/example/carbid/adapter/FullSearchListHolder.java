package com.example.carbid.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;

import java.io.Serializable;

public class FullSearchListHolder extends RecyclerView.ViewHolder implements Serializable {
    TextView nameCarSearchTxt;
    TextView yearCarSearchTxt;
    CardView carSearchButtCard;
    ImageView imageCarSearchItemImg;
    public FullSearchListHolder(@NonNull View itemView) {
        super(itemView);
        nameCarSearchTxt = itemView.findViewById(R.id.nameCarSearch);
        yearCarSearchTxt = itemView.findViewById(R.id.yearCarSearch);
        carSearchButtCard = itemView.findViewById(R.id.carSearchButt);
        imageCarSearchItemImg = itemView.findViewById(R.id.imageCarSearchItem);
    }
}
