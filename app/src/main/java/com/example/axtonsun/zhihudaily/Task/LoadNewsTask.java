package com.example.axtonsun.zhihudaily.Task;

import android.os.AsyncTask;

import com.example.axtonsun.zhihudaily.Bean.Stories;
import com.example.axtonsun.zhihudaily.Net.HttpUtil;
import com.example.axtonsun.zhihudaily.Net.JsonHelper;
import com.example.axtonsun.zhihudaily.View.Adapter.RvAdapter;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;
/**
 * Created by AxtonSun on 2016/8/22.
 */
public class LoadNewsTask extends AsyncTask<Void, Void, List<Stories>> {
    private RvAdapter adapter;
    //private onFinishListener listener;

    public LoadNewsTask(RvAdapter adapter) {
        super();
        this.adapter = adapter;
    }
    @Override
    protected List<Stories> doInBackground(Void... params) {
        List<Stories> storiesList = null;
        try {
            storiesList = JsonHelper.parseJsonToList(HttpUtil.getLastNewsList());
        } catch (IOException | JSONException e) {

        } finally {
            return storiesList;
        }
    }
    @Override
    protected void onPostExecute(List<Stories> storiesList) {
        adapter.refreshNewsList(storiesList);
       /* if (listener != null) {
            listener.afterTaskFinish();
        }*/
    }
   /* public interface onFinishListener {
         void afterTaskFinish();
    }*/
}
