package com.munstein.travelapp.services;

import com.munstein.travelapp.model.TravelPackage;

import java.util.ArrayList;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by @Munstein on 06/01/2018.
 */

public interface ApiService {

    public String baseUrl = "http://private-ce838-desafiobemobi.apiary-mock.com/";

    @GET("travelpackages")
    public Observable<ArrayList<TravelPackage>> getTravelPackages(
            @Query("androidVersion") String androidVersion,
            @Query("brand") String brand,
            @Query("model") String model);
}
