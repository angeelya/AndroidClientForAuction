package com.example.carbid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.ChangeLocationActivity;
import com.example.carbid.ProfileFragment;
import com.example.carbid.R;
import com.example.carbid.app.App;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.authSave.UserSave;
import com.example.carbid.model.Destination;
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

public class LocationUserAdapter extends RecyclerView.Adapter<LocationUserHolder> implements Serializable {
    List<Destination> destinationList;
    TokensSave tokensSave = new TokensSave();
    ChangeLocationActivity changeLocationActivity;
    UserSave userSave = new UserSave();
    public LocationUserAdapter(List<Destination> destinationList, ChangeLocationActivity changeLocationActivity)
    {
        this.destinationList=destinationList;
    this.changeLocationActivity=changeLocationActivity;}
    @NonNull
    @Override
    public LocationUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_location, parent, false);
        return new LocationUserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationUserHolder holder, int position) {
        Destination destination = destinationList.get(position);
        holder.nameLocationTxt.setText(destination.getLocation());
    holder.buttLocationItemC.setOnClickListener(v -> {
     Map<String,String> map = new HashMap<>();
     map.put("id_destination",String.valueOf(destination.getId_destination()));
     map.put("id_user",userSave.getUser(holder.itemView.getContext().getApplicationContext()));
     UsersApi usersApi= new RetrofitService().getRetrofit().create(UsersApi.class);
     usersApi.updateLocation("Bearer " + tokensSave.getAccessToken(holder.itemView.getContext().getApplicationContext()),map).enqueue(new Callback<Map<String, String>>() {
         @Override
         public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
             if  ( !response.body().isEmpty()){
                 if(response.body().get("message").equals("true")){
                     ((App)v.getContext().getApplicationContext()).getProfileFragment().destinationUserTxt.setText(destination.getLocation());
                    changeLocationActivity.finish();
                 }
             }
             else{
                 Toast toast = Toast.makeText(holder.itemView.getContext().getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                 toast.show();
             }
         }

         @Override
         public void onFailure(Call<Map<String, String>> call, Throwable t) {
             if( t instanceof IOException){
                 Logger.getLogger(LocationUserHolder.class.getName()).log(Level.SEVERE, "Ошибка", t);
                 Toast toast;
                 toast = Toast.makeText(v.getContext().getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                 toast.show();}
         }
     });

    });
    }

    @Override
    public int getItemCount() {
        return destinationList.size();
    }
}
