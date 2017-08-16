package com.eros.soundtrack.helper;

import com.eros.soundtrack.enity.Track;
import com.eros.soundtrack.interfaces.Parameters;

import java.util.ArrayList;

/**
 * Created by eroschen on 2017/7/20.
 */

public class PlayerContent {
    private String status = Parameters.Pause;
    private ArrayList<Track> trackList = new ArrayList<>();
    private int curIndex = 0;

}
