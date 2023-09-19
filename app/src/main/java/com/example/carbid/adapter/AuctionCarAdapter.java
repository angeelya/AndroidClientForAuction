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
import com.example.carbid.model.CarRequest;

import java.io.Serializable;
import java.util.Base64;
import java.util.List;

public class AuctionCarAdapter extends RecyclerView.Adapter<AuctionCarHolder> implements Serializable {
    private List<CarRequest> carAuctionRequestList;
    public AuctionCarAdapter (List<CarRequest> carAuctionRequestList){this.carAuctionRequestList = carAuctionRequestList;}
    @NonNull
    @Override
    public AuctionCarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_car, parent, false);

        return new AuctionCarHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuctionCarHolder holder, int position) {
        CarRequest carRequest = carAuctionRequestList.get(position);
     holder.nameCarTxt.setText(carRequest.getName());
     holder.yearCarTxt.setText(carRequest.getYear());
     holder.bidCarTxt.setText(carRequest.getBid());
     byte[] image = Base64.getDecoder().decode(carRequest.getImage());
     Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,image.length);
     holder.imageCarItemImg.setImageBitmap(bitmap);
     holder.carButtCard.setOnClickListener(v -> {
         Intent intent = new Intent(v.getContext(), CarActivity.class)
                 .putExtra("id_car", String.valueOf(carRequest.getId_car()))
                 .putExtra("activity","auctionCar");
         v.getContext().startActivity(intent);
     });
    }

    @Override
    public int getItemCount() {
        return carAuctionRequestList.size();
    }
}
