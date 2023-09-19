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
import com.example.carbid.model.Brand;
import com.example.carbid.model.save.BrandSave;

import java.io.Serializable;
import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandHolder> implements Serializable {
    List<Brand> brandList;
    ItemCarAddActivity itemCarAddActivity;
    BrandSave brandSave = new BrandSave();
    public BrandAdapter(List<Brand> brandList, ItemCarAddActivity itemCarAddActivity) {
       this.brandList = brandList;
       this.itemCarAddActivity = itemCarAddActivity;
    }

    @NonNull
    @Override
    public BrandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_info_add_car, parent, false);
        return new BrandHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandHolder holder, int position) {
     Brand brand = brandList.get(position);
     holder.textInfoCarAddTxt.setText(brand.getName());
     holder.buttInfoForCarAddC.setOnClickListener(v -> {
         brandSave.setID_Brand(v.getContext().getApplicationContext(),String.valueOf(brand.getId_brand()));
         ((App)holder.itemView.getContext().getApplicationContext()).getAddCarActivity().brandCarAddTxt.setText(brand.getName());
         itemCarAddActivity.finish();
     });
    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }
}
