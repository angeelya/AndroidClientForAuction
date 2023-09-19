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
import com.example.carbid.model.save.YearFullSave;

import java.io.Serializable;
import java.util.List;

public class YearFullAdapter extends RecyclerView.Adapter<YearFullHolder> implements Serializable {
    List<Integer> years;
    String ifYear;
    YearFullSave yearFullSave = new YearFullSave();
    public YearFullAdapter(List<Integer> years, String ifYear) {
      this.years = years;
      this.ifYear=ifYear;
    }

    @NonNull
    @Override
    public YearFullHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fullsearchitem, parent, false);
        return new YearFullHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YearFullHolder holder, int position) {
        int year = years.get(position);
        holder.itemListTxt.setText(String.valueOf(year));
        holder.buttItemFullChooseC.setOnClickListener(v -> {
            if(ifYear.equals("before")){
            yearFullSave.setYearBFull(v.getContext().getApplicationContext(),String.valueOf(year));
                ((App)v.getContext().getApplicationContext()).getFullSearchActivity().textYearBeforeTxt.setText(String.valueOf(year));
            }
            else{
            yearFullSave.setYearAFull(v.getContext().getApplicationContext(),String.valueOf(year));
                ((App)v.getContext().getApplicationContext()).getFullSearchActivity().textYearAfterTxt.setText(String.valueOf(year));}
            ((App)v.getContext().getApplicationContext()).getFullItemSearchActivity().finish();
        });
    }

    @Override
    public int getItemCount() {
        return years.size();
    }
}
