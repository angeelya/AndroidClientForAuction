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
import com.example.carbid.SearchActivity;
import com.example.carbid.model.CarRequest;

import java.io.Serializable;
import java.util.Base64;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchHolder> implements Serializable {
    List<CarRequest> carRequestList;
  public SearchAdapter(List<CarRequest> carRequestList){ this.carRequestList=carRequestList;}
    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search, parent, false);

        return new SearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {
    CarRequest carRequest = carRequestList.get(position);
    holder.nameCarSearchTxt.setText(carRequest.getName());
    holder.yearCarSearchTxt.setText(carRequest.getYear());
    byte[] image = Base64.getDecoder().decode(carRequest.getImage());
    Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,image.length);
    holder.imageCarSearchItemImg.setImageBitmap(bitmap);
    holder.carSearchButtCard.setOnClickListener(v -> {
      Intent intent = new Intent(v.getContext(), CarActivity.class)
              .putExtra("id_car", String.valueOf(carRequest.getId_car()))
              .putExtra("activity","search");
      v.getContext().startActivity(intent);
    });
    }

    @Override
    public int getItemCount() {
        return carRequestList.size();
    }
}
