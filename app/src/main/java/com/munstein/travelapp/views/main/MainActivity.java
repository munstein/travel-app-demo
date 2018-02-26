package com.munstein.travelapp.views.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.munstein.travelapp.R;
import com.munstein.travelapp.adapter.TravelPackageAdapter;
import com.munstein.travelapp.base.BaseActivity;
import com.munstein.travelapp.model.TravelPackage;
import com.munstein.travelapp.views.detail.PackageDetailActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements MainMVP.view {

    private MainMVP.presenter mPresenter;
    private RecyclerView mRecyclerView;
    private TravelPackageAdapter mTravelPackageAdapter;
    private MaterialDialog progressDialog;
    private TextView mTextError;
    private RecyclerView.LayoutManager mLayoutManager;
    private Parcelable mState;

    private final String LIST_KEY = "LIST_KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mPresenter = new MainPresenter(this, new MainModel());
        mRecyclerView = findViewById(R.id.main_recyclerview);
        mTextError = findViewById(R.id.main_txt_error);
        progressDialog = new MaterialDialog.Builder(this)
                .title(R.string.progress_dialog_title)
                .content(R.string.progress_dialog_msg)
                .progress(true, 0)
                .progressIndeterminateStyle(true).build();
        mPresenter.loadTravelPackages();
        mTextError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.loadTravelPackages();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mState = mLayoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_KEY, mState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            mState = savedInstanceState.getParcelable(LIST_KEY);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(mState != null){
            mLayoutManager.onRestoreInstanceState(mState);
            hideErrorViews();
            hideProgressDialog();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLayoutManager(newConfig.orientation);
    }

    @Override
    public void showMessage(String msg) {
        showToast(msg);
    }

    @Override
    public void showErrorViews() {
        mTextError.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideErrorViews() {
        mTextError.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void loadTravelPackages(ArrayList<TravelPackage> travelPackages) {
        mTravelPackageAdapter = new TravelPackageAdapter(travelPackages, MainActivity.this);
        mRecyclerView.setAdapter(mTravelPackageAdapter);
        mTravelPackageAdapter.notifyDataSetChanged();
        setLayoutManager(getResources().getConfiguration().orientation);
    }

    @Override
    public void launchDetailActivity(TravelPackage travelPackage) {
        Intent i = new Intent(this, PackageDetailActivity.class);
        Gson gson = new Gson();
        i.putExtra(getString(R.string.extra_travel_package), gson.toJson(travelPackage));
        startActivity(i);
    }

    private void setLayoutManager(final int orientation){
        switch (orientation){
            case Configuration.ORIENTATION_LANDSCAPE:
                mLayoutManager = new GridLayoutManager(this, 2);
                break;

            case Configuration.ORIENTATION_PORTRAIT:
                mLayoutManager = new LinearLayoutManager(MainActivity.this,
                        LinearLayoutManager.VERTICAL, false);
                break;
                default:
                    mLayoutManager = new LinearLayoutManager(MainActivity.this,
                            LinearLayoutManager.VERTICAL, false);
                    break;
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
    }
}