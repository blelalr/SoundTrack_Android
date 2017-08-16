package com.eros.soundtrack.task;

import android.content.Context;

import com.eros.soundtrack.helper.APIHelper;
import com.eros.soundtrack.interfaces.ApiParameters;

/**
 * Created by eroschen on 2017/7/17.
 */

public class GetPopularTask extends ApiBaseTask {

    private String result = null;
    public GetPopularTask(Context context) {
        super(context);
    }

    @Override
    protected String doInBackground(Object... params) {
        try{
            result = APIHelper.getInstance().getPopularMovies();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error: " + e.toString());
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        listener.processFinish(mParser.popularMovies(result), ApiParameters.GET_POPULAR);
    }
}
