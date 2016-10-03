package com.example.achuan.asynctask_easystudy;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by achuan on 16-10-2.
 */

//AsyncTask<1,2,3> 3个参数的介绍：1传入给后台任务的数据的类型,2进度值类型,3返回值类型
    //不需要传入任何值时指定类型为Void
public class MyAsyncTask  extends AsyncTask<Void,Void,Void>{

    String TAG="lyc-changeworld";


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG,"onPreExecute");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d(TAG,"doInBackground");
        publishProgress();

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Log.d(TAG,"onProgressUpdate");

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d(TAG,"onPostExecute");

    }
}
