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
import com.example.carbid.model.MyBidRequest;

import java.io.Serializable;
import java.util.Base64;
import java.util.List;

public class MyBidsAdapter  extends RecyclerView.Adapter<MyBidsHolder>  implements Serializable {
   private List<MyBidRequest> myBidRequestList;
   public  MyBidsAdapter (List<MyBidRequest> myBidRequestList){
        this.myBidRequestList=myBidRequestList;
    }
    @NonNull
    @Override
    public MyBidsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bid, parent, false);
        return new MyBidsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBidsHolder holder, int position) {
       MyBidRequest myBidRequest = myBidRequestList.get(position);
       holder.carNameTxt.setText(myBidRequest.getName());
       holder.priceBidTxt.setText(String.valueOf(myBidRequest.getPrice()));
        byte[] image = Base64.getDecoder().decode(myBidRequest.getImage());
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,image.length);
        holder.carImageImg.setImageBitmap(bitmap);
        holder.buttMyBidCard.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CarActivity.class)
                    .putExtra("id_car", String.valueOf(myBidRequest.getId_car()))
                    .putExtra("activity","myBid");
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return myBidRequestList.size();
    }
}
