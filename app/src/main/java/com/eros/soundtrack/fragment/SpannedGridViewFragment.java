package com.eros.soundtrack.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eros.soundtrack.R;
import com.eros.soundtrack.enity.GridItem;
import com.eros.soundtrack.manager.SpannedGridLayoutManager;

import java.util.ArrayList;

/**
 * Created by eroschen on 2017/7/10.
 */

public class SpannedGridViewFragment extends BaseFragment {

    public ArrayList<GridItem> mData = new ArrayList<>();
    public RecyclerView mRecyclerView;
    public SpannedGridLayoutManager manager;

    public SpannedGridViewFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asymmetric_gridview, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        manager = new SpannedGridLayoutManager(
                new SpannedGridLayoutManager.GridSpanLookup() {
                    @Override
                    public SpannedGridLayoutManager.SpanInfo getSpanInfo(int position) {
                        // Conditions for 2x2 items
                        if (position % 12 == 0 || position %12 == 7) { // || position % 6 == 4) {
                            return new SpannedGridLayoutManager.SpanInfo(2, 2);
                        } else {
                            return new SpannedGridLayoutManager.SpanInfo(1, 1);
                        }
                    }
                },
                3, // number of columns
                1.2f // how big is default item
        );
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);



    }


}