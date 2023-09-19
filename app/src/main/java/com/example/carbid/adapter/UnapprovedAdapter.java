package com.example.carbid.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.model.Unapproved;
import com.example.carbid.retrofit.CarApi;
import com.example.carbid.retrofit.RetrofitService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnapprovedAdapter extends RecyclerView.Adapter<UnapprovedHolder> implements Serializable {
    List<Unapproved> unapprovedList;
    TokensSave tokensSave = new TokensSave();
    public UnapprovedAdapter(List<Unapproved> unapprovedList) {
        this.unapprovedList=unapprovedList;
    }

    @NonNull
    @Override
    public UnapprovedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_car, parent, false);

        return new UnapprovedHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnapprovedHolder holder, int position) {
    Unapproved unapproved = unapprovedList.get(position);
        CarApi carApi = new RetrofitService().getRetrofit().create(CarApi.class);
        Map<String,String> get = new HashMap<>();
        get.put("id_car",String.valueOf(unapproved.getId_car()));
        carApi.selectCarId("Bearer "+tokensSave.getAccessToken(holder.itemView.getContext().getApplicationContext()),get).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if(response.body().isEmpty()){
                    holder.nameCarTxt.setText(response.body().get("name"));
                    holder.bidCarTxt.setText(response.body().get("bid"));
                    holder.yearCarTxt.setText(response.body().get("year"));
                    byte[] image = Base64.getDecoder().decode(response.body().get("image"));
                    Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,image.length);
                    holder.imageCarItemImg.setImageBitmap(bitmap);
                }
                else{
                    Toast toast = Toast.makeText(holder.itemView.getContext().getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(UnapprovedAdapter.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(holder.itemView.getContext().getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }

    @Override
    public int getItemCount() {
        return unapprovedList.size();
    }
}
