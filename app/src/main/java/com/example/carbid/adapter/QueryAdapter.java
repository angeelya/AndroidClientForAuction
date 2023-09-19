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
import com.example.carbid.model.QueryModel;
import com.example.carbid.retrofit.BuyApi;
import com.example.carbid.retrofit.CarApi;
import com.example.carbid.retrofit.QueryApi;
import com.example.carbid.retrofit.RetrofitService;
import com.example.carbid.retrofit.UnapprovedApi;

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

public class QueryAdapter extends RecyclerView.Adapter<QueryHolder> implements Serializable {
    List<QueryModel> queryModelList;
    TokensSave tokensSave = new TokensSave();
   public QueryAdapter (List<QueryModel> queryModelList){this.queryModelList = queryModelList;}
    @NonNull
    @Override
    public QueryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_query_car, parent, false);
        return new QueryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QueryHolder holder, int position) {
       QueryModel queryModel = queryModelList.get(position);
        Map<String,String> request = new HashMap<>();
        request.put("id_car",String.valueOf(queryModel.getId_car()));
        CarApi carApi = new RetrofitService().getRetrofit().create(CarApi.class);
        carApi.findCarName("Bearer "+tokensSave.getAccessToken(holder.itemView.getContext().getApplicationContext()),request).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if(response.body()!=null)
                {
                    Map<String,String> res = response.body();
                    byte[] image = Base64.getDecoder().decode(res.get("image"));
                    Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,image.length);
                    holder.imageQueryCarImg.setImageBitmap(bitmap);
                    holder.nameQueryCarTxt.setText(res.get("name"));
                    //  holder.priceQueryCarTxt.setText(res.get("bid"));
                    holder.yearQueryCarTxt.setText(res.get("year"));
                }
                else{
                    Toast toast = Toast.makeText(holder.itemView.getContext().getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(QueryAdapter.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(holder.itemView.getContext().getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
        holder.priceQueryCarTxt.setText(String.valueOf(queryModel.getPrice()));
        holder.buttYesB.setOnClickListener(v -> {
            queryModelList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, queryModelList.size());

        });
        holder.buttNoB.setOnClickListener(v -> {
            queryModelList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, queryModelList.size());
        });
    }

    private void deleteQuery(QueryHolder holder, List<QueryModel> queryModelList, QueryModel queryModel) {
       Map<String,String> requestDel = new HashMap<>();
       requestDel.put("id_query",String.valueOf(queryModel.getId_query()));
        QueryApi queryApi = new RetrofitService().getRetrofit().create(QueryApi.class);
        queryApi.deleteQuery("Bearer "+tokensSave.getAccessToken(holder.itemView.getContext().getApplicationContext()),requestDel).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if(response.body().isEmpty()){
                    if(response.body().get("message").equals("true")){
                        int pos = holder.getAdapterPosition();
                        queryModelList.remove(pos);
                        notifyItemRemoved(pos);
                        notifyItemRangeChanged(pos, queryModelList.size());
                    }
                    else {
                        Toast toast = Toast.makeText(holder.itemView.getContext().getApplicationContext(), "Не удалось удалить", Toast.LENGTH_LONG);
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
                    Logger.getLogger(QueryAdapter.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(holder.itemView.getContext().getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
    }

    @Override
    public int getItemCount() {
        return queryModelList.size();
    }
}
