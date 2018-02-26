package com.munstein.travelapp.views.main;

import com.munstein.travelapp.model.TravelPackage;

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by @Munstein on 06/01/2018.
 */

public class MainPresenter implements MainMVP.presenter {

    private MainMVP.view mView;
    private MainMVP.model mModel;

    public MainPresenter(MainMVP.view view, MainMVP.model model){
        mView = view;
        mModel = model;
    }

    @Override
    public void loadTravelPackages() {
        mView.showProgressDialog();
        try {
            mModel.getTravelPackages()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<ArrayList<TravelPackage>>() {
                                       @Override
                                       public void onNext(ArrayList<TravelPackage> value) {
                                           mView.hideErrorViews();
                                           mView.loadTravelPackages(value);
                                       }

                                       @Override
                                       public void onError(Throwable e) {
                                           mView.hideProgressDialog();
                                           mView.showErrorViews();
                                       }

                                       @Override
                                       public void onComplete() {
                                           mView.hideProgressDialog();
                                       }
                                   }
                    );
        }catch (Exception x){
            mView.showErrorViews();
            mView.hideProgressDialog();
        }
    }
}
