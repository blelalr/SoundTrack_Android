package com.eros.soundtrack.helper;

import com.eros.soundtrack.interfaces.ApiParameters;
/**
 * Created by eroschen on 2017/7/12.
 */

public class APIHelper implements ApiParameters {
    private static APIHelper instance = null;

    public APIHelper() {

    }

    public static synchronized APIHelper getInstance () {
        if (instance == null) {
            instance = new APIHelper();
        }
        return instance;
    }



    public String getPopularMovies(){

        return new Http().get(whatsong + GET_POPULAR_MOVIES, null);
    }

    public String getRecentMovies(){

        return new Http().get(whatsong + GET_RECENT_MOVIES, null);
    }

    public String getAllMovies(){

        return new Http().get(whatsong + GET_ALL_MOVIES, null);

    }






}
