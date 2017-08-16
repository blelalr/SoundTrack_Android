package com.eros.soundtrack.retrofit;

import com.eros.soundtrack.enity.ResponseData;
import com.eros.soundtrack.helper.SoundTrackInfo;
import com.eros.soundtrack.interfaces.ApiParameters;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eroschen on 2017/7/12.
 */

public class APIHelper implements ApiParameters {
    private static APIHelper instance = null;
    private static APIService service;
    private static APIResponse apiResponse;

    private APIHelper() {
        service = ServiceFactory.createRetrofitService(APIService.class, APIService.SERVICE_ENDPOINT);
    }

    public APIHelper(APIResponse response) {
        service = ServiceFactory.createRetrofitService(APIService.class, APIService.SERVICE_ENDPOINT);
        apiResponse = response;
    }

    public static synchronized APIHelper getInstance () {
        if (instance == null) {
            instance = new APIHelper();
        }
        return instance;
    }

    public void getPopularMovies(int limit, int skip){
        Call<ResponseData> call = service.getPopularMovies(limit, skip);

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()){
                    SoundTrackInfo.getInstance().setPopularMovies(response.body().getDataList());
                    apiResponse.Success(GET_POPULAR);
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                apiResponse.Failure(t.getMessage());
            }
        });

    }

    public void getRecentMovies(int limit, int skip){
        Call<ResponseData> call = service.getRecentMovies(limit, skip);

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()){
                    SoundTrackInfo.getInstance().setRecentMovies(response.body().getDataList());
                    apiResponse.Success(GET_RECENT);
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                apiResponse.Failure(t.getMessage());
            }
        });

    }

}
