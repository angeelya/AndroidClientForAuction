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
import com.example.carbid.model.Model;
import com.example.carbid.model.save.BrandSave;
import com.example.carbid.model.save.ModelSave;

import java.io.Serializable;
import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelHolder> implements Serializable {
    List<Model> modelList;
    ItemCarAddActivity itemCarAddActivity;
    ModelSave modelSave = new ModelSave();
    BrandSave brandSave = new BrandSave();
    public ModelAdapter(List<Model> modelList, ItemCarAddActivity itemCarAddActivity) {
        this.modelList= modelList;
        this.itemCarAddActivity=itemCarAddActivity;
    }

    @NonNull
    @Override
    public ModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_info_add_car, parent, false);
        return new ModelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelHolder holder, int position) {
    Model model = modelList.get(position);
    holder.textInfoCarAddTxt.setText(model.getName());
    holder.buttInfoForCarAddC.setOnClickListener(v -> {
        modelSave.setID_Model(v.getContext().getApplicationContext(),String.valueOf(model.getId_model()));
         brandSave.setID_Brand(v.getContext().getApplicationContext(),"");
        ((App)holder.itemView.getContext().getApplicationContext()).getAddCarActivity().modelCarAddTxt.setText(model.getName());
        itemCarAddActivity.finish();
    });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
