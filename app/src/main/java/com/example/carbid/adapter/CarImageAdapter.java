package com.example.carbid.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;

import java.io.Serializable;
import java.util.Base64;
import java.util.List;

public class CarImageAdapter extends RecyclerView.Adapter<CarImageHolder> implements Serializable {
    List<String> imageList;
    public CarImageAdapter(List<String> imageList) {
       this.imageList = imageList;
    }

    @NonNull
    @Override
    public CarImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image_car, parent, false);
        return new CarImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarImageHolder holder, int position) {
        String imgCar = imageList.get(position);
        byte[] image = Base64.getDecoder().decode(imgCar);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,image.length);
     holder.imgViewCarImg.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
