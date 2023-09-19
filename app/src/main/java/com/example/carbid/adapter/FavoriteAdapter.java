package com.example.carbid.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.CarActivity;
import com.example.carbid.R;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.model.Favorite;
import com.example.carbid.retrofit.CarApi;
import com.example.carbid.retrofit.FavoriteApi;
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

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteHolder>  implements Serializable {
    List<Favorite> favoriteList;
    TokensSave tokensSave = new TokensSave();
    public FavoriteAdapter (List<Favorite> favoriteList){
        this.favoriteList=favoriteList;}
    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite, parent, false);
        return new FavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHolder holder, int position) {
     Favorite favorite = favoriteList.get(position);
        Map<String,String> request = new HashMap<>();
        request.put("id_car",String.valueOf(favorite.getId_car()));
        CarApi carApi = new RetrofitService().getRetrofit().create(CarApi.class);
        carApi.findCarName("Bearer "+tokensSave.getAccessToken(holder.itemView.getContext().getApplicationContext()),request).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if(response.body()!=null)
                {
                   Map<String,String> res = response.body();
                    byte[] image = Base64.getDecoder().decode(res.get("image"));
                    Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,image.length);
                    holder.buttCarFavImg.setImageBitmap(bitmap);
                    holder.nameCarFavTxt.setText(res.get("name"));
                }
                else{
                    Toast toast = Toast.makeText(holder.itemView.getContext().getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(FavoriteAdapter.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(holder.itemView.getContext().getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
      holder.buttDelFavImg.setOnClickListener(view -> {
          Map<String,String> requestDel = new HashMap<>();
          requestDel.put("id_favorite",String.valueOf(favorite.getId_favorite()));
          FavoriteApi favoriteApi = new RetrofitService().getRetrofit().create(FavoriteApi.class);
           favoriteApi.deleteFavorite("Bearer "+tokensSave.getAccessToken(view.getContext().getApplicationContext()),requestDel).enqueue(new Callback<Map<String, String>>() {
               @Override
               public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                   if(response.body()!=null){
                       if(response.body().get("message").equals("true")){
                           int pos = holder.getAdapterPosition();
                           favoriteList.remove(pos);
                           notifyItemRemoved(pos);
                           notifyItemRangeChanged(pos,favoriteList.size());
                       }
                       else {
                           Toast toast = Toast.makeText(view.getContext().getApplicationContext(), "Не удалось удалить", Toast.LENGTH_LONG);
                           toast.show();
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
                       Logger.getLogger(FavoriteAdapter.class.getName()).log(Level.SEVERE, "Ошибка", t);
                       Toast toast;
                       toast = Toast.makeText(holder.itemView.getContext().getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                       toast.show();}
               }
           });
      });
      holder.buttCarFavImg.setOnClickListener(v -> {
          Intent intent = new Intent(v.getContext(), CarActivity.class)
                  .putExtra("id_car", String.valueOf(favorite.getId_car()))
                  .putExtra("activity","favorite");
          v.getContext().startActivity(intent);
      });
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }
}
