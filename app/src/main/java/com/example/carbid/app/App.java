package com.example.carbid.app;

import android.app.Application;
import android.os.Bundle;

import com.example.carbid.AddAuctionActivity;
import com.example.carbid.AddCarActivity;
import com.example.carbid.AuctionListActivity;
import com.example.carbid.CarActivity;
import com.example.carbid.ChangeDataUserActivity;
import com.example.carbid.FullItemSearchActivity;
import com.example.carbid.FullSearchActivity;
import com.example.carbid.HomeFragment;
import com.example.carbid.ListCarForAddAuctionActivity;
import com.example.carbid.ProfileFragment;
import com.example.carbid.Registration;
import com.example.carbid.model.CarAuctionAndFull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App extends Application {
    private Registration registration;
    private CarActivity carActivity;
    private ProfileFragment profileFragment;
    private ChangeDataUserActivity changeDataUserActivity;
    private FullItemSearchActivity fullItemSearchActivity;
    private FullSearchActivity fullSearchActivity;
    private AddAuctionActivity addAuctionActivity;
    private AddCarActivity addCarActivity;
    private CarAuctionAndFull carAuctionAndFull;
private List<Integer> position= new ArrayList<>();
private ListCarForAddAuctionActivity listCarForAddAuctionActivity;
private Map<Long,Integer> pos = new HashMap<>();
private AuctionListActivity auctionListActivity;
private HomeFragment homeFragment;

    public HomeFragment getHomeFragment() {
        return homeFragment;
    }

    public void setHomeFragment(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    public AuctionListActivity getAuctionListActivity() {
        return auctionListActivity;
    }

    public void setAuctionListActivity(AuctionListActivity auctionListActivity) {
        this.auctionListActivity = auctionListActivity;
    }

    public  void setMapPos(int position, long id_car)
    {
       this.pos.put(id_car,position);
    }
    public Map<Long,Integer> getPos()
    {
        return pos;
    }
    public ListCarForAddAuctionActivity getListCarForAddAuctionActivity() {
        return listCarForAddAuctionActivity;
    }

    public void setListCarForAddAuctionActivity(ListCarForAddAuctionActivity listCarForAddAuctionActivity) {
        this.listCarForAddAuctionActivity = listCarForAddAuctionActivity;
    }

    public List<Integer> getPosition() {
        return position;
    }

    public void setPosition(List<Integer> position) {
        this.position = position;
    }


    public CarAuctionAndFull getCarAuctionAndFull() {
        return carAuctionAndFull;
    }

    public void setCarAuctionAndFull(CarAuctionAndFull carAuctionAndFull) {
        this.carAuctionAndFull = carAuctionAndFull;
    }

    public AddCarActivity getAddCarActivity() {
        return addCarActivity;
    }

    public void setAddCarActivity(AddCarActivity addCarActivity) {
        this.addCarActivity = addCarActivity;
    }

    public AddAuctionActivity getAddAuctionActivity() {
        return addAuctionActivity;
    }

    public void setAddAuctionActivity(AddAuctionActivity addAuctionActivity) {
        this.addAuctionActivity = addAuctionActivity;
    }

    public FullSearchActivity getFullSearchActivity() {
        return fullSearchActivity;
    }

    public void setFullSearchActivity(FullSearchActivity fullSearchActivity) {
        this.fullSearchActivity = fullSearchActivity;
    }

    public FullItemSearchActivity getFullItemSearchActivity() {
        return fullItemSearchActivity;
    }

    public void setFullItemSearchActivity(FullItemSearchActivity fullItemSearchActivity) {
        this.fullItemSearchActivity = fullItemSearchActivity;
    }

    public ChangeDataUserActivity getChangeDataUserActivity() {
        return changeDataUserActivity;
    }

    public void setChangeDataUserActivity(ChangeDataUserActivity changeDataUserActivity) {
        this.changeDataUserActivity = changeDataUserActivity;
    }

    public ProfileFragment getProfileFragment() {
        return profileFragment;
    }

    public void setProfileFragment(ProfileFragment profileFragment) {
        this.profileFragment = profileFragment;
    }

    public CarActivity getCarActivity() {
        return carActivity;
    }

    public void setCarActivity(CarActivity carActivity) {
        this.carActivity = carActivity;
    }

    public Registration getRegistration()
    {
        return registration;
    }
    public void setRegistration(Registration registration)
    {
        this.registration=registration;
    }

    public void setPos(Map<Long,Integer> objectObjectHashMap) {
      this.pos = objectObjectHashMap;
    }
}
