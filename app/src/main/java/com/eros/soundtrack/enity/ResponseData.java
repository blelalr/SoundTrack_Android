package com.eros.soundtrack.enity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by eroschen on 2017/8/16.
 */

public class ResponseData {
    @SerializedName("data")
    private ArrayList<GridItem> dataList;

    @SerializedName("is_last_page")
    private Boolean isLastPage;

    public Boolean getLastPage() {
        return isLastPage;
    }

    public void setLastPage(Boolean lastPage) {
        isLastPage = lastPage;
    }

    public ArrayList<GridItem> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<GridItem> dataList) {
        this.dataList = dataList;
    }
}
