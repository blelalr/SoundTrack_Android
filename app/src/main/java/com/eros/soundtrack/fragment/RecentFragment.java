/*
 *  Copyright 2015 Eros Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.eros.soundtrack.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eros.soundtrack.actiity.MainActivity;
import com.eros.soundtrack.adapter.GridViewRecyclerAdapter;
import com.eros.soundtrack.enity.GridItem;
import com.eros.soundtrack.helper.PlayerContent;
import com.eros.soundtrack.helper.SoundTrackInfo;
import com.eros.soundtrack.interfaces.PlayButtonListener;
import com.eros.soundtrack.retrofit.APIHelper;

/**
 * Created by eroschen on 2017/8/30.
 */

public class RecentFragment extends SpannedGridViewFragment implements PlayButtonListener{
    private static GridViewRecyclerAdapter myAdapter;
    private int page = 1;
    private boolean loading = false;
    private int visibleItemCount, firstVisibleItem, lastVisibleItem;
    private MainActivity.TrackOnLoaded mTrackOnLoaded;

    public RecentFragment(MainActivity.TrackOnLoaded mTrackOnLoaded) {
        this.mTrackOnLoaded = mTrackOnLoaded;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myAdapter = new GridViewRecyclerAdapter(mAct, mData, this);
        mRecyclerView.setAdapter(myAdapter);

        APIHelper first = new APIHelper() {
            @Override
            protected void OnLoaded() {
                myAdapter.mData = SoundTrackInfo.getInstance().getRecentMovies();
                myAdapter.notifyDataSetChanged();
                Log.d("eros", "page: "+page);
            }

            @Override
            protected void onFail(String message) {

            }
        };
        first.getRecentMovies(page);

        mRecyclerView.addOnScrollListener( new EndlessScrollListener());
    }

    public static void initData(){
        for(GridItem item: myAdapter.mData){
            item.setPlaying(false);
        }
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(final int position) {

        APIHelper getTrackList = new APIHelper() {
            @Override
            protected void OnLoaded() {
                if (myAdapter.mData.get(position).getPlaying()) {
                    myAdapter.mData.get(position).setPlaying(false);
                    PlayerContent.getInstance().setPlaying(false);
                    myAdapter.notifyDataSetChanged();
                    mTrackOnLoaded.hideMediaPlayer();
                } else {
                    for(GridItem item: myAdapter.mData){
                        item.setPlaying(false);
                    }
                    PopularFragment.initData();
                    myAdapter.mData.get(position).setPlaying(true);
                    PlayerContent.getInstance().setPlaying(true);
                    myAdapter.notifyDataSetChanged();
                    mTrackOnLoaded.showMediaPlayer(SoundTrackInfo.getInstance().getTrackList().get(0));

                }
            }

            @Override
            protected void onFail(String message) {

            }
        };
        getTrackList.getMovieInfo(SoundTrackInfo.getInstance().getRecentMovies().get(position).getId());
    }

    private class EndlessScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (loading)
                return;

            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 2 >= manager.getItemCount()) {
                page = page + 1;
                APIHelper loadMore = new APIHelper() {
                    @Override
                    protected void OnLoaded() {
                        loading = false;
                        myAdapter.mData = SoundTrackInfo.getInstance().getRecentMovies();
                        myAdapter.notifyDataSetChanged();
                        Log.d("eros", "page: "+page);
                    }

                    @Override
                    protected void onFail(String message) {

                    }
                };
                loadMore.getRecentMovies(page);
                loading = true;
            }


        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            visibleItemCount = recyclerView.getChildCount();
            firstVisibleItem = manager.getFirstVisibleItemPosition();
            lastVisibleItem = (firstVisibleItem + visibleItemCount);
        }

    }
}
