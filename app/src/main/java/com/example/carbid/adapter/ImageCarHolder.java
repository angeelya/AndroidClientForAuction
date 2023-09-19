package com.example.carbid.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;

import java.io.Serializable;

public class ImageCarHolder extends RecyclerView.ViewHolder implements Serializable {
    ImageView imageCarItemAddImg;
    ImageView buttDelImgB;
    public ImageCarHolder(@NonNull View itemView) {
        super(itemView);
        imageCarItemAddImg= itemView.findViewById(R.id.imageCarItemAdd);
        buttDelImgB = itemView.findViewById(R.id.buttDelImg);
    }
}
