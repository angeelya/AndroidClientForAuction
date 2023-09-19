package com.example.carbid.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.CarBidActivity;
import com.example.carbid.LocationRegister;
import com.example.carbid.R;
import com.example.carbid.Registration;
import com.example.carbid.app.App;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.authSave.UserSave;
import com.example.carbid.model.Destination;
import com.example.carbid.model.save.DestinationSave;
import com.example.carbid.retrofit.RetrofitService;
import com.example.carbid.retrofit.UsersApi;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRegisterAdapter extends RecyclerView.Adapter<LocationRegisterHolder> implements Serializable {
    List<Destination> destinationList;
    LocationRegister locationRegister;
    DestinationSave destinationSave= new DestinationSave();
    public LocationRegisterAdapter(List<Destination> destinationList, LocationRegister locationRegister)
    { this.destinationList=destinationList;
    this.locationRegister=locationRegister;
    }
    @NonNull
    @Override
    public LocationRegisterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_location, parent, false);
        return new LocationRegisterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationRegisterHolder holder, int position) {
        Destination destination = destinationList.get(position);
        holder.nameLocationTxt.setText(destination.getLocation());
        holder.buttLocationItemC.setOnClickListener(v -> {
            destinationSave.setLocation(v.getContext().getApplicationContext(),String.valueOf(destination.getId_destination()));
            ((App)holder.itemView.getContext().getApplicationContext()).getRegistration().textButtLocationTxt.setText(destination.getLocation());
            locationRegister.finish();
        });
    }

    @Override
    public int getItemCount() {
        return destinationList.size();
    }
}
