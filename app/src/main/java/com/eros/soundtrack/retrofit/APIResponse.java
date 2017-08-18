package com.eros.soundtrack.retrofit;

/**
 * Created by eroschen on 2017/8/16.
 */

public interface APIResponse {
    void Success(Object o, int from);
    void Failure(String message);

}
