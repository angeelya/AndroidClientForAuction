package com.example.carbid;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.carbid.app.App;
import com.example.carbid.authSave.AdminSave;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.authSave.UserSave;
import com.example.carbid.databinding.ActivityCarBidBinding;
import com.example.carbid.model.save.CountViewSave;
import com.example.carbid.retrofit.RecommendApi;
import com.example.carbid.retrofit.RetrofitService;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarBidActivity extends AppCompatActivity implements Serializable {
  ActivityCarBidBinding binding;
  UserSave userSave = new UserSave();
  TokensSave tokensSave = new TokensSave();
  AdminSave adminSave = new AdminSave();
  CountViewSave countViewSave = new CountViewSave();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCarBidBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());
   binding.bottomNavigationView.setOnItemSelectedListener(item -> {
       switch (item.getItemId())
       {
           case R.id.home: replaceFragment(new HomeFragment()); break;
           case R.id.recommend: checkRecommend();break;
           case R.id.profile: checkLogin();break;
       }
       return true;
   });
    }

    private void checkLogin() {
        if (!userSave.getUser(getApplicationContext()).equals(""))
            replaceFragment(new ProfileFragment());
        else
        {
            Intent intent = new Intent(CarBidActivity.this,Authentication.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.login_menu,menu);
        MenuItem loginItem = menu.findItem(R.id.login);
        MenuItem logoutItem = menu.findItem(R.id.logout);
        loginItem.setVisible(userSave.getUser(getApplicationContext()).equals(""));
        logoutItem.setVisible(!userSave.getUser(getApplicationContext()).equals(""));
        return true;
    }
     @Override
     public boolean onOptionsItemSelected(MenuItem menuItem)
     {
         switch (menuItem.getItemId())
         {
             case R.id.login:
                 Intent intent = new Intent(CarBidActivity.this,Authentication.class);
                 startActivity(intent);
                 invalidateMenu();
                 return true;
             case R.id.logout:
                 userSave.setUser(getApplicationContext(),"");
                 adminSave.setModKey(getApplicationContext(),false);
                 tokensSave.setAccessToken(getApplicationContext(),"");
                 tokensSave.setRefreshToken(getApplicationContext(),"");
                 countViewSave.setCount(getApplicationContext(),"0");
                 ((App)getApplicationContext()).getHomeFragment().buttAdminB.setVisibility(View.GONE);
                 invalidateMenu();
                 return true;
             default: return super.onOptionsItemSelected(menuItem);
         }
     }
    private void checkRecommend() {
        if(!userSave.getUser(getApplicationContext()).equals("")) {
            Map<String, String> map = new HashMap<>();
            map.put("id_user", userSave.getUser(getApplicationContext()));
            RecommendApi recommendApi = new RetrofitService().getRetrofit().create(RecommendApi.class);
            recommendApi.checkRecommend("Bearer " + tokensSave.getAccessToken(getApplicationContext()), map).enqueue(new Callback<Map<String, String>>() {
                @Override
                public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                    if (response.body()!=null) {
                        if (response.body().get("message").equals("true"))
                            replaceFragment(new RecommendFragment());
                        else {
                            Intent intent = new Intent(CarBidActivity.this, SearchActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Повторите попытку", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }

                @Override
                public void onFailure(Call<Map<String, String>> call, Throwable t) {
                    if( t instanceof IOException){
                        Logger.getLogger(CarBidActivity.class.getName()).log(Level.SEVERE, "Ошибка", t);
                        Toast toast;
                        toast = Toast.makeText(getApplicationContext(),"Нет соединения с сервером",Toast.LENGTH_LONG);
                        toast.show();}
                }
            });
        }
        else{
            Intent intent = new Intent(CarBidActivity.this,Authentication.class);
            startActivity(intent);
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}