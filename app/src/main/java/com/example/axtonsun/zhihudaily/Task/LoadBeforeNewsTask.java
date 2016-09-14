package com.example.axtonsun.zhihudaily.Task;

import android.os.AsyncTask;
import android.util.Log;

import com.example.axtonsun.zhihudaily.Bean.Stories;
import com.example.axtonsun.zhihudaily.Net.HttpUtil;
import com.example.axtonsun.zhihudaily.Net.JsonHelper;
import com.example.axtonsun.zhihudaily.Utility.Utility;
import com.example.axtonsun.zhihudaily.View.Adapter.RvAdapter;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by AxtonSun on 2016/9/13.
 */
public class LoadBeforeNewsTask extends AsyncTask<Void, Void, List<Stories>> {

    private RvAdapter adapter;
    private onFinishListener listener;
/*
    public LoadBeforeNewsTask(RvAdapter adapter) {
        super();
        this.adapter = adapter;
    }*/
    public LoadBeforeNewsTask(RvAdapter adapter, onFinishListener listener) {
        super();
        this.adapter = adapter;
        this.listener = listener;
    }

    @Override
    protected List<Stories> doInBackground(Void... params) {
        List<Stories> storiesList = null;
        try {
            storiesList = JsonHelper.parseJsonToList(HttpUtil.getBeforeNewsList(Utility.getTime()));
        } catch (IOException | JSONException e) {
            Log.i("loadBeforeNewsTask", String.valueOf(storiesList) + "before storieslist");
            e.printStackTrace();
        } finally {
            return storiesList;
        }
    }

    @Override
    protected void onPostExecute(List<Stories> storiesList) {
        adapter.LoadMoreNewslist(storiesList);
        if (listener != null) {
            listener.afterTaskFinish();
        }
    }
    public interface onFinishListener {
        public void afterTaskFinish();
    }
}
