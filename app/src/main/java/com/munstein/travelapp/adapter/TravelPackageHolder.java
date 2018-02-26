package com.munstein.travelapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.munstein.travelapp.R;

/**
 * Created by @Munstein on 06/01/2018.
 */

public class TravelPackageHolder extends RecyclerView.ViewHolder{

    TextView mTxtPrice;
    TextView mTxtTitle;
    ImageView mImgViewBanner;
    CardView mCardView;

    public TravelPackageHolder(View itemView) {
        super(itemView);
        mCardView = itemView.findViewById(R.id.holder_cardview);
        mTxtPrice = itemView.findViewById(R.id.holder_txt_price);
        mTxtTitle = itemView.findViewById(R.id.holder_txt_title);
        mImgViewBanner = itemView.findViewById(R.id.holder_imgview_banner);
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        mCardView.setOnClickListener(onClickListener);
    }

}
