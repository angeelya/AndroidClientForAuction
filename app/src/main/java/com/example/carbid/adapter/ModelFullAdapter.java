package com.example.carbid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.FullItemSearchActivity;
import com.example.carbid.FullSearchActivity;
import com.example.carbid.R;
import com.example.carbid.app.App;
import com.example.carbid.model.Model;
import com.example.carbid.model.save.ModelFullSave;

import java.io.Serializable;
import java.util.List;

public class ModelFullAdapter extends RecyclerView.Adapter<ModelFullHolder> implements Serializable {
    List<Model> modelList;
    ModelFullSave modelFullSave = new ModelFullSave();
    public ModelFullAdapter(List<Model> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ModelFullHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fullsearchitem, parent, false);
        return new ModelFullHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelFullHolder holder, int position) {
        Model model = modelList.get(position);
     holder.itemListTxt.setText(model.getName());
     holder.buttItemFullChooseC.setOnClickListener(v -> {
         modelFullSave.setID_ModelFull(v.getContext().getApplicationContext(),String.valueOf(model.getId_model()));
         ((App)v.getContext().getApplicationContext()).getFullSearchActivity().textModelTxt.setText(model.getName());
         ((App)v.getContext().getApplicationContext()).getFullItemSearchActivity().finish();
     });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
