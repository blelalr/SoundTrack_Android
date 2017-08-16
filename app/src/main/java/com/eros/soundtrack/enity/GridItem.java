package com.eros.soundtrack.enity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eroschen on 2017/7/10.
 */

public class GridItem {
    @SerializedName("_id")
    private String id;
    @SerializedName("poster_url")
    private String posterURL;
    @SerializedName("poster")
    private String posterURL_2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getPosterURL_2() {
        return posterURL_2;
    }

    public void setPosterURL_2(String posterURL_2) {
        this.posterURL_2 = posterURL_2;
    }
}
