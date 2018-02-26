package com.munstein.travelapp.views.detail;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.munstein.travelapp.R;
import com.munstein.travelapp.base.BaseActivity;
import com.munstein.travelapp.model.TravelPackage;
import com.squareup.picasso.Picasso;

public class PackageDetailActivity extends BaseActivity implements PackageDetailMVP.view {

    private TravelPackage mTravelPackage;
    private TextView mTxtTitle;
    private TextView mTxtDescription;
    private TextView mTxtPrice;
    private Button mBtnBuy;
    private ImageView mImgViewBanner;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private PackageDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.detail_toolbar));
        init();
    }

    private void init(){
        Gson gson = new Gson();
        String json = getIntent().getStringExtra(getString(R.string.extra_travel_package));
        mPresenter = new PackageDetailPresenter(this);
        mTravelPackage = gson.fromJson(json, TravelPackage.class);
        mCollapsingToolbarLayout = findViewById(R.id.detail_collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle(mTravelPackage.getTitle());
        mCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        mTxtTitle = findViewById(R.id.detail_txt_title);
        mTxtDescription = findViewById(R.id.detail_txt_description);
        mBtnBuy = findViewById(R.id.detail_btn_buy);
        mImgViewBanner = findViewById(R.id.detail_imageview_banner);
        mTxtPrice = findViewById(R.id.detail_txt_price);
        mBtnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.buyTravelPackage();
            }
        });
        loadTravelPackage();
    }

    @Override
    public void loadTravelPackage() {
        mTxtTitle.setText(mTravelPackage.getTitle());
        mTxtDescription.setText(mTravelPackage.getDescription());
        mTxtPrice.setText(getString(R.string.promotional) + mTravelPackage.getFormattedPrice());
        Picasso.with(this).load(mTravelPackage.getImgUrl())
                .resize(1200, 800)
                .centerInside()
                .into(mImgViewBanner);
    }

    @Override
    public void showDefaultPaymentFinishedDialog() {
        new MaterialDialog.Builder(this)
                .title(getString(R.string.dialog_payment_title))
                .content(getString(R.string.dialog_payment_msg))
                .positiveText(R.string.dialog_positive_text)
                .positiveColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }

}
