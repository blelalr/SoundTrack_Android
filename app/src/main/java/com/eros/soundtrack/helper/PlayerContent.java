package com.eros.soundtrack.helper;

import com.eros.soundtrack.enity.Track;
import com.eros.soundtrack.interfaces.Parameters;

/**
 * Created by eroschen on 2017/7/20.
 */

public class PlayerContent {
    private static PlayerContent instance;
    private boolean isPlaying = false;
    private Track track;
    private String cover;
    private int curIndex = 0;
    private String mode = Parameters.Mini;

    public static PlayerContent getInstance() {
        if (instance == null){
            synchronized (PlayerContent.class) {
                instance = new PlayerContent();
            }
        }
        return instance;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getCurIndex() {
        return curIndex;
    }

    public void setCurIndex(int curIndex) {
        this.curIndex = curIndex;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}
