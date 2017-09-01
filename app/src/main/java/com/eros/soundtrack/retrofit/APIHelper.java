package com.eros.soundtrack.retrofit;

import com.eros.soundtrack.enity.GridData;
import com.eros.soundtrack.enity.TrackData;
import com.eros.soundtrack.helper.PlayerContent;
import com.eros.soundtrack.helper.SoundTrackInfo;
import com.eros.soundtrack.interfaces.Parameters;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eroschen on 2017/7/12.
 */

public abstract class APIHelper { //implements ApiParameters{
    private static APIService service;

    public APIHelper() {
        service = ServiceFactory.createRetrofitService(APIService.class, APIService.SERVICE_ENDPOINT);
    }

    public void getPopularMovies(int page){
        Call<GridData> call = service.getPopularMovies(page);

        call.enqueue(new Callback<GridData>() {
            @Override
            public void onResponse(Call<GridData> call, Response<GridData> response) {

                if(response.isSuccessful()){
                    SoundTrackInfo.getInstance().addPopularMovies(response.body().getDataList());
                    OnLoaded();
                }
            }

            @Override
            public void onFailure(Call<GridData> call, Throwable t) {
                onFail(t.getMessage());
            }
        });

    }

    public void getRecentMovies(int page){
        Call<GridData> call = service.getRecentMovies(page);

        call.enqueue(new Callback<GridData>() {
            @Override
            public void onResponse(Call<GridData> call, Response<GridData> response) {
                if(response.isSuccessful()){
                    SoundTrackInfo.getInstance().addRecentMovies(response.body().getDataList());
                    OnLoaded();
                }
            }

            @Override
            public void onFailure(Call<GridData> call, Throwable t) {
                onFail(t.getMessage());
            }
        });

    }

    public void getMovieInfo(int id){
        Call<TrackData> call = service.getMovieInfo(id);

        call.enqueue(new Callback<TrackData>() {
            @Override
            public void onResponse(Call<TrackData> call, Response<TrackData> response) {
                if(response.isSuccessful()){
                    SoundTrackInfo.getInstance().setTrackList(response.body().getTrackInfo().getDataList());
                    PlayerContent.getInstance().setCover(response.body().getTrackInfo().getCover());
                    PlayerContent.getInstance().setCurIndex(0);
                    PlayerContent.getInstance().setMode(Parameters.Mini);
                    OnLoaded();
                }
            }

            @Override
            public void onFailure(Call<TrackData> call, Throwable t) {
                onFail(t.getMessage());
            }
        });

    }

    protected abstract void OnLoaded();

    protected abstract void onFail(String message);



}
