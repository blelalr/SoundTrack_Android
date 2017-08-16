package com.eros.soundtrack.task;

import android.content.Context;
import android.util.Log;

import com.eros.soundtrack.interfaces.ApiParameters;

/**
 * Created by eroschen on 2017/7/17.
 */

public class GetAllTask extends ApiBaseTask {

    private String result = null;
    public GetAllTask(Context context) {
        super(context);
    }

    @Override
    protected String doInBackground(Object... params) {
        try{
            result = "what";//APIHelper.getInstance().getAllMovies();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error: " + e.toString());
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d("eros", "all task run");

        listener.processFinish("Done" , ApiParameters.GET_ALL);

    }
}
