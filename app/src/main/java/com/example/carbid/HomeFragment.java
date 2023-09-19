package com.example.carbid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.carbid.app.App;
import com.example.carbid.authSave.AdminSave;
import com.example.carbid.authSave.UserSave;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements Serializable {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    UserSave userSave = new UserSave();
    AdminSave adminSave= new AdminSave();
   public Button buttAdminB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        CardView buttAuctionsCard = rootView.findViewById(R.id.buttAuctions);
        buttAdminB = rootView.findViewById(R.id.buttAdmin);
        buttAdminB.setVisibility(View.GONE);
        ((App)rootView.getContext().getApplicationContext()).setHomeFragment(this);
        if(adminSave.getModKey(rootView.getContext().getApplicationContext()))
        { buttAdminB.setVisibility(View.VISIBLE);}
        buttAdminB.setOnClickListener(v -> {
            Intent in = new Intent(HomeFragment.this.getActivity(),PanelAdminActivity.class);
            startActivity(in);
        });
        buttAuctionsCard.setOnClickListener(v -> {
            Intent intent = new Intent(HomeFragment.this.getActivity(),AuctionListActivity.class);
            startActivity(intent);
        });
        CardView buttSearchCard = rootView.findViewById(R.id.buttSearch);
        buttSearchCard.setOnClickListener(v -> {
            if(!userSave.getUser(getActivity().getApplicationContext()).equals(""))
            {
                Intent intent = new Intent(HomeFragment.this.getActivity(),SearchActivity.class);
                startActivity(intent);}
            else{
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),"Вы не авторизированы",Toast.LENGTH_LONG);
                toast.show();
            }});
        CardView buttMyBidsCard = rootView.findViewById(R.id.buttMyBids);
        buttMyBidsCard.setOnClickListener(v -> {
            if(!userSave.getUser(getActivity().getApplicationContext()).equals(""))
            {
            Intent intent= new Intent(HomeFragment.this.getActivity(),MyBidActivity.class);
            startActivity(intent);}
            else{
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),"Вы не авторизированы",Toast.LENGTH_LONG);
                toast.show();
            }});
            CardView buttViewCarsCard = rootView.findViewById(R.id.buttViewCars);
            buttViewCarsCard.setOnClickListener(v -> {
            if(!userSave.getUser(getActivity().getApplicationContext()).equals(""))
            {
                Intent intent = new Intent(HomeFragment.this.getActivity(),ViewCarActivity.class);
                startActivity(intent);}
            else{
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),"Вы не авторизированы",Toast.LENGTH_LONG);
                toast.show();
            }});
        CardView buttFavoritesCard = rootView.findViewById(R.id.buttFavorites);
        buttFavoritesCard.setOnClickListener(v -> {
            if(!userSave.getUser(getActivity().getApplicationContext()).equals(""))
            {
                Intent intent = new Intent(HomeFragment.this.getActivity(),FavoriteActivity.class);
                startActivity(intent);}
            else{
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),"Вы не авторизированы",Toast.LENGTH_LONG);
                toast.show();
            }});
        CardView buttResultAuctionCard = rootView.findViewById(R.id.buttResultAuction);
        buttResultAuctionCard.setOnClickListener(v -> {
            if(!userSave.getUser(getActivity().getApplicationContext()).equals(""))
            {
                Intent intent = new Intent(HomeFragment.this.getActivity(),StatisticActivity.class);
                startActivity(intent);}
            else{
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),"Вы не авторизированы",Toast.LENGTH_LONG);
                toast.show();
            }});
        CardView buttAdvancedSearchCard = rootView.findViewById(R.id.buttAdvancedSearch);
        buttAdvancedSearchCard.setOnClickListener(v -> {
            if(!userSave.getUser(getActivity().getApplicationContext()).equals(""))
            {
                Intent intent = new Intent(HomeFragment.this.getActivity(),FullSearchActivity.class);
                startActivity(intent);}
            else{
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),"Вы не авторизированы",Toast.LENGTH_LONG);
                toast.show();
            }
               });
        return rootView;
    }
}