package com.example.carbid.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.AddCarActivity;
import com.example.carbid.R;

import java.io.Serializable;
import java.util.List;

public class ImageCarAdapter extends RecyclerView.Adapter<ImageCarHolder> implements Serializable {
    List<Bitmap> bitmaps;
    AddCarActivity addCarActivity;
    public ImageCarAdapter(List<Bitmap> bitmaps, AddCarActivity addCarActivity) {
     this.bitmaps = bitmaps;
     this.addCarActivity = addCarActivity;
    }

    @NonNull
    @Override
    public ImageCarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_img_add_car, parent, false);
        return new ImageCarHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageCarHolder holder, int position) {
        Bitmap bitmap = bitmaps.get(position);
      holder.imageCarItemAddImg.setImageBitmap(bitmap);
      holder.buttDelImgB.setOnClickListener(v -> {
          if(bitmaps.size()==1)
             addCarActivity.listImgCarAddR.setVisibility(View.GONE);
          bitmaps.remove(position);
          addCarActivity.imageCarList.remove(position);
          notifyItemRemoved(position);
          notifyItemRangeChanged(position,bitmaps.size());
      });
    }

    @Override
    public int getItemCount() {
        return bitmaps.size();
    }
}
