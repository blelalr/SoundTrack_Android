package com.eros.soundtrack.retrofit;

import com.eros.soundtrack.enity.ResponseData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by eroschen on 2017/8/16.
 */

public interface APIService {

    String SERVICE_ENDPOINT = "http://www.api.what-song.com/";

    @GET("popular-movies")
    Call<ResponseData> getPopularMovies(@Query("page") int page);

    @GET("recent-movies")
    Call<ResponseData> getRecentMovies(@Query("page") int page);
}
