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

public class AuctionImgAdapter extends RecyclerView.Adapter<AuctionImgHolder> implements Serializable {
    List<String> imageList;
    public AuctionImgAdapter(List<String> imageList){
        this.imageList=imageList;
    }
    @NonNull
    @Override
    public AuctionImgHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_img_auction, parent, false);

        return new AuctionImgHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuctionImgHolder holder, int position) {
     String img = imageList.get(position);
        byte[] image = Base64.getDecoder().decode(img);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,image.length);
     holder.imgViewCarActionRealImg.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
