package com.example.carbid.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.AuctionActivity;
import com.example.carbid.AuctionCarActivity;
import com.example.carbid.R;
import com.example.carbid.authSave.UserSave;
import com.example.carbid.model.AuctionListRequest;

import java.io.Serializable;
import java.util.List;

public class AuctionListAdapter extends RecyclerView.Adapter<AuctionListHolder> implements Serializable {
    private List<AuctionListRequest> auctionListRequestList;
   private UserSave userSave = new UserSave();
    private Context context;
    public AuctionListAdapter(List<AuctionListRequest> auctionListRequestList) {
        this.auctionListRequestList = auctionListRequestList;
    }
    @NonNull
    @Override
    public AuctionListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_auction, parent, false);

        return new AuctionListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuctionListHolder holder, int position) {context = holder.itemView.getContext();
    AuctionListRequest auctionListRequest = auctionListRequestList.get(position);
    holder.nameAuctionTxt.setText(auctionListRequest.getName());
    holder.locationAuctionTxt.setText(auctionListRequest.getLocation());
    holder.statusAuctionTxt.setText(auctionListRequest.getStatus());
    holder.dateAddAuctionTxt.setText(auctionListRequest.getDate_auction());
    holder.auctionButtCard.setOnClickListener(v -> {
        if(!userSave.getUser(v.getContext()).equals(""))
        {   if(auctionListRequest.getStatus().equals("Будущий")){
            Intent intent = new Intent(v.getContext(), AuctionCarActivity.class)
                    .putExtra("id_auction",String.valueOf( auctionListRequest.getId_auction()))
                    .putExtra("name", auctionListRequest.getName())
                    .putExtra("location", auctionListRequest.getLocation())
                    .putExtra("date", auctionListRequest.getDate_auction());
            v.getContext().startActivity(intent);}
            else{
                Intent intent = new Intent(v.getContext(), AuctionActivity.class);
                intent.putExtra("id_auction",String.valueOf(auctionListRequest.getId_auction()));
                v.getContext().startActivity(intent);
            }
        }
        else {
            Toast toast = Toast.makeText(context.getApplicationContext(),"Вы не авторизированы",Toast.LENGTH_LONG);
            toast.show();
        }
    });
    }

    @Override
    public int getItemCount() {
        return auctionListRequestList.size();
    }
}
