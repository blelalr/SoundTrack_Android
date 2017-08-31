package com.eros.soundtrack.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.eros.soundtrack.R;
import com.eros.soundtrack.actiity.MainActivity;
import com.eros.soundtrack.enity.GridItem;

import java.util.ArrayList;

/**
 * Created by eroschen on 2017/7/11.
 */

public class GridViewRecyclerAdapter extends RecyclerView.Adapter<GridViewRecyclerAdapter.SimpleViewHolder> {
    private final MainActivity mAct;
    public ArrayList<GridItem> mData;

    public GridViewRecyclerAdapter(MainActivity mAct, ArrayList<GridItem> data) {
        this.mAct = mAct;
        this.mData = data;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mAct).inflate(R.layout.grid_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {

        Glide.with(mAct)
                .load(mData.get(position).getPosterURL())
                .centerCrop()
                .placeholder(R.color.colorAccent)
                .listener(requestListener)
                .error(R.color.white50Transparent)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mImageView;

        public SimpleViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.iv_poster);
        }
    }

    private RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            // todo log exception

            // important to return false so the error placeholder can be placed
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            return false;
        }
    };


}
