package com.example.carbid.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.AddAuctionActivity;
import com.example.carbid.R;
import com.example.carbid.app.App;
import com.example.carbid.model.CarId;
import com.example.carbid.model.ImageCar;

import java.io.Serializable;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class CarListAuctionAdapter extends RecyclerView.Adapter<CarListAuctionHolder> implements Serializable {
    List<CarId> carIdList;
    public CarListAuctionAdapter(List<CarId> carIdList) {
       this.carIdList =carIdList;
    }

    @NonNull
    @Override
    public CarListAuctionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_car_auction_add_activ, parent, false);
        return new CarListAuctionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarListAuctionHolder holder, int position) {
        CarId carId = carIdList.get(position);
        holder.nameCarAuctionActivityTxt.setText(carId.getName());
        byte[] image = Base64.getDecoder().decode(carId.getImage());
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,image.length);
        holder.buttCarAuctionImg.setImageBitmap(bitmap);
        holder.buttDelCarAuctionBImg.setOnClickListener(v -> {
            Map<Long, Integer> map= ((App)v.getContext().getApplicationContext()).getPos();
           List<Integer> positions =((App)v.getContext().getApplicationContext()).getPosition();
                  Integer pos = map.get(Long.valueOf(carIdList.get(position).getId_car()));
                   positions.remove(pos);
            ((App)v.getContext().getApplicationContext()).setPosition(positions);
            carIdList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,carIdList.size());
        });
    }

    @Override
    public int getItemCount() {
        return carIdList.size();
    }
}
