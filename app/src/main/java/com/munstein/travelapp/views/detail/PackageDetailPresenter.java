package com.munstein.travelapp.views.detail;

/**
 * Created by @Munstein on 07/01/2018.
 */

public class PackageDetailPresenter implements PackageDetailMVP.presenter {

    private final PackageDetailMVP.view mView;

    public PackageDetailPresenter(PackageDetailMVP.view view){
        this.mView = view;
    }

    @Override
    public void buyTravelPackage() {
        mView.showDefaultPaymentFinishedDialog();
    }
}
