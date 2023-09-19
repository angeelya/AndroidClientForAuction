package com.example.carbid.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.CarActivity;
import com.example.carbid.R;
import com.example.carbid.model.CarAuctionAndFull;

import java.io.Serializable;
import java.util.Base64;
import java.util.List;

public class FullSearchListAdapter extends RecyclerView.Adapter<FullSearchListHolder> {
    List<CarAuctionAndFull> carFullSearchList;
    public FullSearchListAdapter(List<CarAuctionAndFull> carFullSearchList) {
      this.carFullSearchList=carFullSearchList;
    }

    @NonNull
    @Override
    public FullSearchListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search, parent, false);
        return new FullSearchListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FullSearchListHolder holder, int position) {
        CarAuctionAndFull carFullSearch = carFullSearchList.get(position);
    holder.nameCarSearchTxt.setText(carFullSearch.getName());
    holder.yearCarSearchTxt.setText(carFullSearch.getYear());
        byte[] image = Base64.getDecoder().decode(carFullSearch.getImage());
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,image.length);
        holder.imageCarSearchItemImg.setImageBitmap(bitmap);
        holder.carSearchButtCard.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CarActivity.class)
                    .putExtra("id_car", String.valueOf(carFullSearch.getId_car()))
                    .putExtra("activity","FullSearch");
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return carFullSearchList.size();
    }
}
