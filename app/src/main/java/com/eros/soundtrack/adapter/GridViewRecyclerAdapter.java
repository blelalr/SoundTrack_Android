package com.eros.soundtrack.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.eros.soundtrack.R;
import com.eros.soundtrack.actiity.MainActivity;
import com.eros.soundtrack.enity.GridItem;
import com.eros.soundtrack.interfaces.PlayButtonListener;

import java.util.ArrayList;

/**
 * Created by eroschen on 2017/7/11.
 */

public class GridViewRecyclerAdapter extends RecyclerView.Adapter<GridViewRecyclerAdapter.SimpleViewHolder> {
    private final MainActivity mAct;
    public  ArrayList<GridItem> mData;
    private PlayButtonListener mListerer;


    public GridViewRecyclerAdapter(MainActivity mAct, ArrayList<GridItem> data, PlayButtonListener listener) {
        this.mAct = mAct;
        this.mData = data;
        this.mListerer = listener;

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
                .into(holder.ivPoster);

        if (mData.get(position).getPlaying()){
            holder.ibPlayStatus.setImageResource(R.drawable.stop);
        } else {
            holder.ibPlayStatus.setImageResource(R.drawable.play);
        }


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        public ImageView ivPoster;
        public ImageButton ibPlayStatus;

        public SimpleViewHolder(View view) {
            super(view);
            ivPoster = (ImageView) view.findViewById(R.id.iv_poster);
            ibPlayStatus = (ImageButton) view.findViewById(R.id.ib_play_status);
            ibPlayStatus.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListerer.onClick(getAdapterPosition());
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
