package com.munstein.travelapp.views.detail;

/**
 * Created by @Munstein on 06/01/2018.
 */

public interface PackageDetailMVP {

    interface view{
        void loadTravelPackage();
        void showDefaultPaymentFinishedDialog();
    }

    interface presenter{
        void buyTravelPackage();
    }

}
