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
import com.example.carbid.model.RecommendRequest;

import java.io.Serializable;
import java.util.Base64;
import java.util.List;


public class RecommendAdapter extends RecyclerView.Adapter<RecommendHolder> implements Serializable {
    List<RecommendRequest> recommendRequestList;
    public RecommendAdapter( List<RecommendRequest> recommendRequestList){this.recommendRequestList=recommendRequestList;}
    @NonNull
    @Override
    public RecommendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_car, parent, false);
        return new RecommendHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendHolder holder, int position) {
    RecommendRequest recommendRequest = recommendRequestList.get(position);
    holder.nameCarTxt.setText(recommendRequest.getName());
    holder.yearCarTxt.setText(recommendRequest.getYear());
    holder.bidCarTxt.setText(recommendRequest.getBid());
        byte[] image = Base64.getDecoder().decode(recommendRequest.getImage());
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,image.length);
        holder.imageCarItemImg.setImageBitmap(bitmap);
        holder.carButtCard.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CarActivity.class)
                    .putExtra("id_car", String.valueOf(recommendRequest.getId_car()))
                    .putExtra("activity","Recommend");
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recommendRequestList.size();
    }
}
