package com.munstein.travelapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.munstein.travelapp.R;
import com.munstein.travelapp.model.TravelPackage;
import com.munstein.travelapp.views.main.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by @Munstein on 06/01/2018.
 */

public class TravelPackageAdapter extends RecyclerView.Adapter<TravelPackageHolder> {

    private ArrayList<TravelPackage> mTravelPackageArrayList;
    private Context mContext;

    public TravelPackageAdapter(ArrayList<TravelPackage> travelPackages, Context context){
        mTravelPackageArrayList = travelPackages;
        mContext = context;
    }


    @Override
    public TravelPackageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.travel_package_holder, parent, false);
        TravelPackageHolder holder = new TravelPackageHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TravelPackageHolder holder, int position) {
        final TravelPackage travelPackage = mTravelPackageArrayList.get(position);
        holder.mTxtTitle.setText(travelPackage.getTitle());
        holder.mTxtPrice.setText(mContext.getString(R.string.promotional)
                + travelPackage.getFormattedPrice());
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)mContext).launchDetailActivity(travelPackage);
            }
        });
        Picasso.with(mContext).load(travelPackage.getImgUrl())
                .resize(1200, 800)
                .centerInside()
                .into(holder.mImgViewBanner);
    }

    @Override
    public int getItemCount() {
        return mTravelPackageArrayList.size();
    }

}
