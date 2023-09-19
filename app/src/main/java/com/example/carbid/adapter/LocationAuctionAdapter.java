package com.example.carbid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.AddAuctionActivity;
import com.example.carbid.LocationForAddAuctionActivity;
import com.example.carbid.R;
import com.example.carbid.app.App;
import com.example.carbid.model.LocationAuction;
import com.example.carbid.model.save.LocationAuctionSave;

import java.io.Serializable;
import java.util.List;

public class LocationAuctionAdapter extends RecyclerView.Adapter<LocationAuctionHolder> implements Serializable {
    List<LocationAuction> locationAuctionList;
    LocationForAddAuctionActivity locationForAddAuctionActivity;
    LocationAuctionSave locationAuctionSave = new LocationAuctionSave();
    public LocationAuctionAdapter(List<LocationAuction> locationAuctionList, LocationForAddAuctionActivity locationForAddAuctionActivity)
    {
        this.locationAuctionList=locationAuctionList;
    this.locationForAddAuctionActivity=locationForAddAuctionActivity;}
    @NonNull
    @Override
    public LocationAuctionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_location, parent, false);
        return new LocationAuctionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAuctionHolder holder, int position) {
       LocationAuction locationAuction = locationAuctionList.get(position);
       holder.nameLocationTxt.setText(locationAuction.getLocation());
       holder.buttLocationItemC.setOnClickListener(v -> {
           locationAuctionSave.setLocation(v.getContext().getApplicationContext(),String.valueOf(locationAuction.getId_location()));
           ((App)v.getContext().getApplicationContext()).getAddAuctionActivity().locationAddAuctionTxt.setText(locationAuction.getLocation());
           locationForAddAuctionActivity.finish();
       });
    }
    @Override
    public int getItemCount() {
        return locationAuctionList.size();
    }
}
