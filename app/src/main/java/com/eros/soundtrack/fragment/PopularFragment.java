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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

public class PopularFragment extends SpannedGridViewFragment implements PlayButtonListener{
    private static GridViewRecyclerAdapter myAdapter;
    private int page = 1;
    private boolean loading = false;
    private int lastVisibleItem;
    private MainActivity.TrackOnLoadListener mTrackOnLoaded;

    public PopularFragment(MainActivity.TrackOnLoadListener trackOnLoaded) {
        this.mTrackOnLoaded = trackOnLoaded;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        myAdapter = new GridViewRecyclerAdapter(mData, this);
        mRecyclerView.setAdapter(myAdapter);
        APIHelper first = new APIHelper() {
            @Override
            protected void OnLoaded() {
                myAdapter.mData = SoundTrackInfo.getInstance().getPopularMovies();
                myAdapter.notifyDataSetChanged();
            }

            @Override
            protected void onFail(String message) {

            }
        };
        first.getPopularMovies(page);

        mRecyclerView.addOnScrollListener(new EndlessScrollListener());

        final Toast mToast = Toast.makeText(mAct, "", Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER, 0, 0);

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
                    RecentFragment.initData();
                    myAdapter.mData.get(position).setPlaying(true);
                    PlayerContent.getInstance().setPlaying(true);
                    myAdapter.notifyDataSetChanged();
                    if(SoundTrackInfo.getInstance().getTrackList().size() != 0) {
                        mTrackOnLoaded.showMediaPlayer(SoundTrackInfo.getInstance().getTrackList().get(0));


                    }

                }
            }

            @Override
            protected void onFail(String message) {

            }
        };
        getTrackList.getMovieInfo(SoundTrackInfo.getInstance().getPopularMovies().get(position).getId());

    }

    private class EndlessScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (loading)
                return;

            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem +2 >= manager.getItemCount()){
                page += 1;
                APIHelper loadMore = new APIHelper() {
                    @Override
                    protected void OnLoaded() {
                        loading = false;
                        myAdapter.mData = SoundTrackInfo.getInstance().getPopularMovies();
                        myAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onFail(String message) {

                    }
                };
                loadMore.getPopularMovies(page);
                loading = true;

            }


        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int visibleItemCount = recyclerView.getChildCount();
            int firstVisibleItem = manager.getFirstVisibleItemPosition();
            lastVisibleItem = (firstVisibleItem + visibleItemCount);
        }

    }

    public static void initData(){
        for(GridItem item: myAdapter.mData){
            item.setPlaying(false);
        }
        myAdapter.notifyDataSetChanged();
    }

}
