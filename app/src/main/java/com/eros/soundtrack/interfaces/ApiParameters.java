package com.eros.soundtrack.interfaces;

/**
 * Created by eroschen on 2017/7/14.
 */

public interface ApiParameters {

    //API URL
    String whatsong = "http://www.api.what-song.com/";


    //APIs
    String GET_POPULAR_MOVIES = "popular-movies?limit=100&skip=0";
    String GET_RECENT_MOVIES = "recent-movies?limit=100&skip=0";
    String GET_ALL_MOVIES = "all-movies";


    int GET_POPULAR = 0x00;
    int GET_RECENT  = 0x01;
    int GET_ALL     = 0x02;

}
