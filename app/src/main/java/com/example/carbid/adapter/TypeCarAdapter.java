package com.example.carbid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.AddCarActivity;
import com.example.carbid.ItemCarAddActivity;
import com.example.carbid.R;
import com.example.carbid.app.App;
import com.example.carbid.model.TypeCar;
import com.example.carbid.model.save.TypeCarSave;

import java.io.Serializable;
import java.util.List;

public class TypeCarAdapter extends RecyclerView.Adapter<TypeCarHolder> implements Serializable {
    List<TypeCar> typeCarList;
    ItemCarAddActivity itemCarAddActivity;
    TypeCarSave typeCarSave = new TypeCarSave();
    public TypeCarAdapter(List<TypeCar> typeCarList, ItemCarAddActivity itemCarAddActivity)
    {
        this.typeCarList=typeCarList;
        this.itemCarAddActivity=itemCarAddActivity;
    }
    @NonNull
    @Override
    public TypeCarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_info_add_car, parent, false);
        return new TypeCarHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeCarHolder holder, int position) {
     TypeCar typeCar = typeCarList.get(position);
     holder.textInfoCarAddTxt.setText(typeCar.getName());
     holder.buttInfoForCarAddC.setOnClickListener(v -> {
      typeCarSave.setID_TypeCar(v.getContext().getApplicationContext(),String.valueOf(typeCar.getId_typecar()));
         ((App)holder.itemView.getContext().getApplicationContext()).getAddCarActivity().typeTransportCarAddTxt.setText(typeCar.getName());
      itemCarAddActivity.finish();
     });
    }

    @Override
    public int getItemCount() {
        return typeCarList.size();
    }
}
