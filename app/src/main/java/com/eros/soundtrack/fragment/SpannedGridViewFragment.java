package com.eros.soundtrack.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eros.soundtrack.R;
import com.eros.soundtrack.actiity.MainActivity;
import com.eros.soundtrack.adapter.GridViewRecyclerAdapter;
import com.eros.soundtrack.enity.GridItem;
import com.eros.soundtrack.manager.SpannedGridLayoutManager;

import java.util.ArrayList;

/**
 * Created by eroschen on 2017/7/10.
 */

public class SpannedGridViewFragment extends Fragment {

    private final ArrayList<GridItem> mData;
    private RecyclerView gridView;
    private MainActivity mAct;

    public SpannedGridViewFragment(ArrayList<GridItem> data) {
        this.mData = data;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mAct = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asymmetric_gridview, container, false);
        gridView = (RecyclerView)view.findViewById(R.id.recyclerView);

        GridViewRecyclerAdapter myAdapter = new GridViewRecyclerAdapter(mAct, mData);

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
        gridView.setLayoutManager(manager);
        gridView.setAdapter(myAdapter);
        return view;
    }




}