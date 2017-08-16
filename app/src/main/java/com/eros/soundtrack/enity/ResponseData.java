package com.eros.soundtrack.enity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by eroschen on 2017/8/16.
 */

public class ResponseData {
    @SerializedName("data")
    private ArrayList<GridItem> dataList;

    public ArrayList<GridItem> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<GridItem> dataList) {
        this.dataList = dataList;
    }
}
