package com.eros.soundtrack.retrofit;

import com.eros.soundtrack.enity.GridData;
import com.eros.soundtrack.enity.TrackData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by eroschen on 2017/8/16.
 */

public interface APIService {

    String SERVICE_ENDPOINT = "http://www.api.what-song.com/";

    @GET("popular-movies")
    Call<GridData> getPopularMovies(@Query("page") int page);

    @GET("recent-movies")
    Call<GridData> getRecentMovies(@Query("page") int page);

    @GET("movie-info")
    Call<TrackData> getMovieInfo(@Query("movieID") int id);



}
