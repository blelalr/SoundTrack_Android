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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eros.soundtrack.actiity.MainActivity;
import com.eros.soundtrack.adapter.GridViewRecyclerAdapter;
import com.eros.soundtrack.helper.SoundTrackInfo;
import com.eros.soundtrack.manager.ItemClickSupport;
import com.eros.soundtrack.retrofit.APIHelper;

/**
 * Created by eroschen on 2017/8/30.
 */

public class PopularFragment extends SpannedGridViewFragment {
    private GridViewRecyclerAdapter myAdapter;
    private int page = 1;
    private boolean loading = false;
    private int lastVisibleItem;
    private EndlessScrollListener endlessScrollListener = new EndlessScrollListener();
    private MainActivity.TrackOnLoaded mTrackOnLoaded;

    public PopularFragment(MainActivity.TrackOnLoaded trackOnLoaded) {
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

        myAdapter = new GridViewRecyclerAdapter(mAct, mData);
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

        mRecyclerView.addOnScrollListener(endlessScrollListener);

        final Toast mToast = Toast.makeText(mAct, "", Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER, 0, 0);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Log.d("eros", "Item clicked: " + position);
                APIHelper getTrackList = new APIHelper() {
                    @Override
                    protected void OnLoaded() {
                        mTrackOnLoaded.showMediaPlayer(SoundTrackInfo.getInstance().getTrackList().get(0));
                    }

                    @Override
                    protected void onFail(String message) {

                    }
                };
                getTrackList.getMovieInfo(SoundTrackInfo.getInstance().getPopularMovies().get(position).getId());

            }

        });


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
}
