package com.example.carbid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbid.adapter.RecommendAdapter;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.authSave.UserSave;
import com.example.carbid.model.RecommendRequest;
import com.example.carbid.retrofit.RecommendApi;
import com.example.carbid.retrofit.RetrofitService;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecommendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendFragment extends Fragment implements Serializable {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecommendFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecommendFragment newInstance(String param1, String param2) {
        RecommendFragment fragment = new RecommendFragment();
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
    RecyclerView listRecommendR;
    ProgressBar progressBarRecommendB;
    TokensSave tokensSave = new TokensSave();
    UserSave userSave = new UserSave();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recommmend, container, false);
       listRecommendR = rootView.findViewById(R.id.listRec);
       progressBarRecommendB = rootView.findViewById(R.id.progressBarRecommend);
        listRecommendR.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        RecommendApi recommendApi = new RetrofitService().getRetrofit().create(RecommendApi.class);
        Map<String,String> request = new HashMap<>();
        request.put("id_user",userSave.getUser(rootView.getContext().getApplicationContext()));
        recommendApi.getRecommend("Bearer " + tokensSave.getAccessToken(rootView.getContext().getApplicationContext()),request).enqueue(new Callback<List<RecommendRequest>>() {
            @Override
            public void onResponse(Call<List<RecommendRequest>> call, Response<List<RecommendRequest>> response) {
                if (response.body() != null) {

                if  ( !response.body().isEmpty()){
                    progressBarRecommendB.setVisibility(View.GONE);
                    getListRecommend(response.body());
                }
                else{
                    Toast toast = Toast.makeText(rootView.getContext().getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
                }
                else{
                    Toast toast = Toast.makeText(rootView.getContext().getApplicationContext(),"Повторите попытку",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<RecommendRequest>> call, Throwable t) {
                if( t instanceof IOException){
                    Logger.getLogger(RecommendFragment.class.getName()).log(Level.SEVERE, "Ошибка", t);
                    Toast toast;
                    toast = Toast.makeText(rootView.getContext().getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                    toast.show();}
            }
        });
        return rootView;
        }

    private void getListRecommend(List<RecommendRequest> recommendRequestList) {
        RecommendAdapter recommendAdapter = new RecommendAdapter(recommendRequestList);
        listRecommendR.setAdapter(recommendAdapter);
    }

}