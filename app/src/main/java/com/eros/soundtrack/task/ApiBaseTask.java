package com.eros.soundtrack.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.eros.soundtrack.helper.Parser;
import com.eros.soundtrack.interfaces.AsyncResponse;

/**
 * Created by eroschen on 2017/7/17.
 */

public class ApiBaseTask extends AsyncTask<Object, Object, String>  {
    protected ProgressDialog progressDialog;
    protected boolean disableProgress;
    protected Parser mParser = new Parser();
    public AsyncResponse listener = null;

    public ApiBaseTask(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (progressDialog != null && !disableProgress) {
            progressDialog.show();
        }
    }

    @Override
    protected String doInBackground(Object... params) {

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
