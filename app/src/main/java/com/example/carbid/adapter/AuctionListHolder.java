package com.example.carbid.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.R;

import java.io.Serializable;

public class AuctionListHolder extends RecyclerView.ViewHolder implements Serializable {
    TextView nameAuctionTxt;
    TextView locationAuctionTxt;
    TextView statusAuctionTxt;
    TextView dateAddAuctionTxt;
    CardView auctionButtCard;
    public AuctionListHolder(@NonNull View itemView) {
        super(itemView);
        nameAuctionTxt = itemView.findViewById(R.id.nameAuction);
        locationAuctionTxt = itemView.findViewById(R.id.locationAuction);
        statusAuctionTxt = itemView.findViewById(R.id.statusAuction);
        dateAddAuctionTxt = itemView.findViewById(R.id.dateAddAuction);
        auctionButtCard = itemView.findViewById(R.id.auctionButt);
    }
}
