package com.munstein.travelapp.views.main;

import com.munstein.travelapp.model.TravelPackage;

import java.util.ArrayList;

import io.reactivex.Observable;


/**
 * Created by @Munstein on 06/01/2018.
 */

public interface MainMVP {

    interface model{
        Observable<ArrayList<TravelPackage>> getTravelPackages();
    }

    interface view{
        void showMessage(String msg);
        void showErrorViews();
        void hideErrorViews();
        void showProgressDialog();
        void hideProgressDialog();
        void loadTravelPackages(ArrayList<TravelPackage> travelPackages);
        void launchDetailActivity(TravelPackage travelPackage);
    }

    interface presenter{
        void loadTravelPackages();
    }


}
