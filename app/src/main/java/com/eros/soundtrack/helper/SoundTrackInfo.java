package com.eros.soundtrack.helper;


import com.eros.soundtrack.enity.GridItem;

import java.util.ArrayList;

/**
 * Created by eroschen on 2017/7/14.
 */

public class SoundTrackInfo {
    private static SoundTrackInfo instance;
    public static SoundTrackInfo getInstance() {
        if (instance == null){
            synchronized (SoundTrackInfo.class) {
                instance = new SoundTrackInfo();
            }
        }

        return instance;
    }

    private ArrayList<GridItem> recentMovies = new ArrayList<>();
    private ArrayList<GridItem> popularMovies = new ArrayList<>();
    private ArrayList<GridItem> allMovies = new ArrayList<>();
    private PlayerContent playerContent = new PlayerContent();

    public ArrayList<GridItem> getRecentMovies() {
        return recentMovies;
    }

    public void setRecentMovies(ArrayList<GridItem> recentMovies) {
        this.recentMovies = recentMovies;
    }

    public ArrayList<GridItem> getPopularMovies() {
        return popularMovies;
    }

    public void setPopularMovies(ArrayList<GridItem> popularMovies) {
        this.popularMovies = popularMovies;
    }

    public ArrayList<GridItem> getAllMovies() {
        return allMovies;
    }

    public void setAllMovies(ArrayList<GridItem> allMovies) {
        this.allMovies = allMovies;
    }
}
