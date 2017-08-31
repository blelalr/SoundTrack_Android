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

package com.eros.soundtrack.enity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by eroschen on 2017/8/31.
 */

public class TrackInfo {
    @SerializedName("CompleteListOfSongs")
    private ArrayList<Track> dataList;
    @SerializedName("poster")
    private String cover;

    public ArrayList<Track> getDataList() {
        return dataList;
    }

    public String getCover() {
        return cover;
    }


}
