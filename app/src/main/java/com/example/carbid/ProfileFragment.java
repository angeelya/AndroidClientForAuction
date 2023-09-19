package com.example.carbid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.versionedparcelable.NonParcelField;
import androidx.versionedparcelable.ParcelField;

import com.example.carbid.app.App;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.authSave.UserSave;
import com.example.carbid.retrofit.RetrofitService;
import com.example.carbid.retrofit.UsersApi;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment{
    CarBidActivity carBidActivity;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
      this.carBidActivity = carBidActivity;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
   public TextView destinationUserTxt;
    TextView nameUserTxt;
   public TextView emailUserTxt;
    CardView ChangeDataC;
    UserSave userSave = new UserSave();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        TokensSave tokensSave = new TokensSave();
        nameUserTxt = rootView.findViewById(R.id.nameUser);
         emailUserTxt = rootView.findViewById(R.id.emailUser);
        destinationUserTxt = rootView.findViewById(R.id.destinationUser);
        ChangeDataC = rootView.findViewById(R.id.ChangeData);
        Map<String,String> request = new HashMap<>();
        request.put("id_user",userSave.getUser(rootView.getContext().getApplicationContext()));
        UsersApi usersApi = new RetrofitService().getRetrofit().create(UsersApi.class);
        usersApi.getDataUser("Bearer " + tokensSave.getAccessToken(rootView.getContext().getApplicationContext()),request).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if  (response.body()!=null){
                    nameUserTxt.setText(response.body().get("name"));
                    emailUserTxt.setText(response.body().get("email"));
                    destinationUserTxt.setText(response.body().get("location"));
                }
                else{
                    Toast toast = Toast.makeText(rootView.getContext().getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(ProfileFragment.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(rootView.getContext().getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
        ChangeDataC.setOnClickListener(v -> {
            ((App)v.getContext().getApplicationContext()).setProfileFragment(this);
            Intent intent = new Intent(ProfileFragment.this.getActivity(),ChangeDataUserActivity.class);
            startActivity(intent);
        });
        destinationUserTxt.setOnClickListener(v -> {
            ((App)v.getContext().getApplicationContext()).setProfileFragment(this);
            Intent intent = new Intent(ProfileFragment.this.getContext(),ChangeLocationActivity.class);
            startActivity(intent);
        });
        return rootView;
    }
}