package com.eros.soundtrack.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eros.soundtrack.R;
import com.eros.soundtrack.actiity.MainActivity;
import com.eros.soundtrack.adapter.GridViewRecyclerAdapter;
import com.eros.soundtrack.enity.GridItem;
import com.eros.soundtrack.helper.SoundTrackInfo;
import com.eros.soundtrack.interfaces.ApiParameters;
import com.eros.soundtrack.manager.ItemClickSupport;
import com.eros.soundtrack.manager.SpannedGridLayoutManager;
import com.eros.soundtrack.retrofit.APIHelper;
import com.eros.soundtrack.retrofit.APIResponse;

import java.util.ArrayList;

/**
 * Created by eroschen on 2017/7/10.
 */

public class SpannedGridViewFragment extends Fragment implements APIResponse{

    private ArrayList<GridItem> mData;
//    private TwoWayView mRecyclerView;
    private RecyclerView mRecyclerView;
    private MainActivity mAct;
    private APIHelper apiHelper;
    private int popPage = 1;
    private int recPage = 1;
    private int mIndex;
    private GridViewRecyclerAdapter myAdapter;

    public SpannedGridViewFragment(ArrayList<GridItem> data, int index) {
        this.mData = data;
        this.apiHelper = new APIHelper(this);
        this.mIndex = index;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mAct = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asymmetric_gridview, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        myAdapter = new GridViewRecyclerAdapter(mAct, mData);
        mRecyclerView.setAdapter(myAdapter);

        SpannedGridLayoutManager manager = new SpannedGridLayoutManager(
                new SpannedGridLayoutManager.GridSpanLookup() {
                    @Override
                    public SpannedGridLayoutManager.SpanInfo getSpanInfo(int position) {
                        // Conditions for 2x2 items
                        if (position % 6 == 0) { // || position % 6 == 4) {
                            return new SpannedGridLayoutManager.SpanInfo(2, 2);
                        } else {
                            return new SpannedGridLayoutManager.SpanInfo(1, 1);
                        }
                    }
                },
                3, // number of columns
                1.4f // how big is default item
        );
        mRecyclerView.setLayoutManager(manager);


        final Toast mToast = Toast.makeText(mAct, "", Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER, 0, 0);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                mToast.setText("Item clicked: " + position);
                mToast.show();
            }

        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                updateState(scrollState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                Log.d("eros", "First: " + mRecyclerView.getFirstVisiblePosition());
//                Log.d("eros", "Count: " + mRecyclerView.getChildCount());
//                View lastChildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount() - 1);
//                int lastChildBottom = lastChildView.getBottom();
//                int recyclerBottom = recyclerView.getBottom() - recyclerView.getPaddingBottom();
//                int lastPosition = recyclerView.getLayoutManager().getPosition(lastChildView);
//
//                if (lastChildBottom == recyclerBottom && lastPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
                
//                if(mIndex == ApiParameters.GET_POPULAR){
//                        if(mRecyclerView.getChildCount() == SoundTrackInfo.getInstance().getPopularMovies().size()){
//                            popPage = popPage + 1;
//                            apiHelper.getPopularMovies(popPage);
//                        }
//                    }
//                    if(mIndex == ApiParameters.GET_RECENT){
//                        if(mRecyclerView.getChildCount() == SoundTrackInfo.getInstance().getRecentMovies().size()) {
//                            recPage = recPage + 1;
//                            apiHelper.getRecentMovies(recPage);
//                        }
//                    }
//
//                }
            }
        });


        return view;
    }

    public void setData(ArrayList<GridItem> mData) {
        this.mData = mData;
    }

    @Override
    public void Success(final Object isLastPage, final int from) {
        if ((Boolean) isLastPage) {
            Log.d("eros", "isLastPage: " + isLastPage);
            return;
        }

        if(from == ApiParameters.GET_POPULAR)
            setData(SoundTrackInfo.getInstance().getPopularMovies());

        if(from == ApiParameters.GET_RECENT)
            setData(SoundTrackInfo.getInstance().getRecentMovies());

        myAdapter.notifyItemRangeChanged(0, myAdapter.getItemCount());
    }

    @Override
    public void Failure(String message) {

    }
}