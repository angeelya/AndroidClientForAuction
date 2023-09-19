package com.example.carbid.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;
import com.example.carbid.app.App;
import com.example.carbid.model.CarAuctionAndFull;

import java.util.Base64;
import java.util.List;

public class ListCarForAddAuctionAdapter extends RecyclerView.Adapter<ListCarForAddAuctionHolder> {
    List<CarAuctionAndFull> carAuctionAndFulls;
    public ListCarForAddAuctionAdapter(List<CarAuctionAndFull> carAuctionAndFulls)
    {
        this.carAuctionAndFulls = carAuctionAndFulls;
    }
    @NonNull
    @Override
    public ListCarForAddAuctionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_add_auction_car, parent, false);
        return new ListCarForAddAuctionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCarForAddAuctionHolder holder, int position) {
        List<Integer> positions = ((App)holder.itemView.getContext().getApplicationContext()).getPosition();
            CarAuctionAndFull carAuctionAndFull = carAuctionAndFulls.get(position);
            holder.nameCarAddAuctionTxt.setText(carAuctionAndFull.getName());
            holder.yearAddCarAuctionTxt.setText(carAuctionAndFull.getYear());
            byte[] image = Base64.getDecoder().decode(carAuctionAndFull.getImage());
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            holder.imageViewCarAuctionAddImg.setImageBitmap(bitmap);
            holder.buttAddCarAucC.setOnClickListener(v -> {
                positions.add(position);
                ((App)v.getContext().getApplicationContext()).setPosition(positions);
                ((App)v.getContext().getApplicationContext()).setMapPos(position,carAuctionAndFull.getId_car());
                ((App)v.getContext().getApplicationContext()).setCarAuctionAndFull(carAuctionAndFull);
                ((App)v.getContext().getApplicationContext()).getAddAuctionActivity().loadCar();
               ((App)v.getContext().getApplicationContext()).getListCarForAddAuctionActivity().finish();
            });
    }

    @Override
    public int getItemCount() {
        return carAuctionAndFulls.size();
    }
}
