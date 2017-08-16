package com.eros.soundtrack.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.eros.soundtrack.R;
import com.eros.soundtrack.actiity.MainActivity;
import com.eros.soundtrack.enity.GridItem;

import java.util.ArrayList;

/**
 * Created by eroschen on 2017/7/11.
 */

public class GridViewRecyclerAdapter extends RecyclerView.Adapter<GridViewRecyclerAdapter.ViewHolder> {
    private MainActivity mAct;
    private ArrayList<GridItem> mData;

    public GridViewRecyclerAdapter(MainActivity mAct, ArrayList<GridItem> data) {
        this.mAct = mAct;
        this.mData = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public ViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.iv_poster);
        }
    }

    @Override
    public GridViewRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        holder.mImageView.getLayoutParams().height = 2/3 * metrics.widthPixels;

        Glide.with(mAct)
                .load(mData.get(position).getPosterURL())
                .centerCrop()
                .placeholder(R.color.colorAccent)
                .error(R.color.white50Transparent)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


}
