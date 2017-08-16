package com.eros.soundtrack.helper;

import com.eros.soundtrack.enity.GridItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by eroschen on 2017/7/14.
 */

public class Parser {

    public Parser() {
    }

    public ArrayList<GridItem> popularMovies(Object response) {
        ArrayList<GridItem> popularMovies = new ArrayList<>();

        try {
            JSONObject jObject = new JSONObject(String.valueOf(response));
            JSONArray jArray = jObject.getJSONArray("data");

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject c = jArray.getJSONObject(i);
                GridItem gridItem = new GridItem();
                gridItem.setId(String.valueOf(c.getInt("_id")));
                gridItem.setPosterURL(c.getString("poster_url"));
                gridItem.setPosterURL_2(c.getString("poster"));
                popularMovies.add(gridItem);
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }

        return popularMovies;
    }

    public ArrayList<GridItem> recentMovies(Object response) {
        ArrayList<GridItem> recentMovies = new ArrayList<>();

        try {
            JSONObject jObject = new JSONObject(String.valueOf(response));
            JSONArray jArray = jObject.getJSONArray("data");

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject c = jArray.getJSONObject(i);
                GridItem gridItem = new GridItem();
                gridItem.setId(String.valueOf(c.getInt("_id")));
                gridItem.setPosterURL(String.valueOf(c.getString("poster_url")));
                gridItem.setPosterURL_2(c.getString("poster"));
                recentMovies.add(gridItem);
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }

        return recentMovies;
    }

}
