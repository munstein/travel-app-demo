package com.munstein.travelapp.views.main;

import android.os.Build;

import com.munstein.travelapp.model.TravelPackage;
import com.munstein.travelapp.services.ApiService;
import com.munstein.travelapp.services.RetrofitBuilder;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by @Munstein on 06/01/2018.
 */

public class MainModel implements MainMVP.model {
    @Override
    public Observable<ArrayList<TravelPackage>> getTravelPackages() {
        ApiService apiService = RetrofitBuilder.
                getRetrofit(ApiService.baseUrl).create(ApiService.class);
        return apiService.getTravelPackages(Build.VERSION.RELEASE, Build.BRAND, Build.MODEL);
    }
}
