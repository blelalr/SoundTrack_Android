package com.eros.soundtrack.helper;


import com.eros.soundtrack.enity.GridItem;
import com.eros.soundtrack.enity.Track;

import java.util.ArrayList;

/**
 * Created by eroschen on 2017/7/14.
 */

public class SoundTrackInfo {
    private static SoundTrackInfo instance;

    private ArrayList<GridItem> recentMovies = new ArrayList<>();
    private ArrayList<GridItem> popularMovies = new ArrayList<>();
    private ArrayList<GridItem> allMovies = new ArrayList<>();
    private ArrayList<Track> trackList = new ArrayList<>();

    public static SoundTrackInfo getInstance() {
        if (instance == null){
            synchronized (SoundTrackInfo.class) {
                instance = new SoundTrackInfo();
            }
        }

        return instance;
    }

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

    public void addPopularMovies(ArrayList<GridItem> popularMovies){
        for(GridItem movie: popularMovies){
            this.popularMovies.add(movie);
        }

    }
    public void addRecentMovies(ArrayList<GridItem> recentMovies){
        for(GridItem movie: recentMovies){
            this.recentMovies.add(movie);
        }
    }


    public ArrayList<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(ArrayList<Track> trackList) {
        this.trackList = trackList;
    }

}
