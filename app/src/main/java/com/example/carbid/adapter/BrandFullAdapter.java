package com.example.carbid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.carbid.R;
import com.example.carbid.app.App;
import com.example.carbid.model.Brand;
import com.example.carbid.model.save.BrandFullSave;

import java.io.Serializable;
import java.util.List;

public class BrandFullAdapter extends RecyclerView.Adapter<BrandFullHolder> implements Serializable {
    List<Brand> brandList;
    BrandFullSave brandFullSave = new BrandFullSave();
    public BrandFullAdapter(List<Brand> brandList) {
        this.brandList= brandList;
    }

    @NonNull
    @Override
    public BrandFullHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fullsearchitem, parent, false);
        return new BrandFullHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandFullHolder holder, int position) {
        Brand brand = brandList.get(position);
     holder.itemListTxt.setText(brand.getName());
     holder.buttItemFullChooseC.setOnClickListener(v -> {
         brandFullSave.setID_BrandFull(holder.itemView.getContext().getApplicationContext(),String.valueOf(brand.getId_brand()));
         ((App)v.getContext().getApplicationContext()).getFullSearchActivity().textBrandTxt.setText(brand.getName());
         ((App)v.getContext().getApplicationContext()).getFullItemSearchActivity().finish();
     });
    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }
}
