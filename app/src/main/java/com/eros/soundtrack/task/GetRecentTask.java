package com.eros.soundtrack.task;

import android.content.Context;
import android.util.Log;

import com.eros.soundtrack.helper.APIHelper;
import com.eros.soundtrack.interfaces.ApiParameters;

/**
 * Created by eroschen on 2017/7/17.
 */

public class GetRecentTask extends ApiBaseTask {

    private String result = null;
    public GetRecentTask(Context context) {
        super(context);
    }

    @Override
    protected String doInBackground(Object... params) {
        try{
            result = APIHelper.getInstance().getRecentMovies();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error: " + e.toString());
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        listener.processFinish(mParser.recentMovies(result), ApiParameters.GET_RECENT);

    }
}
