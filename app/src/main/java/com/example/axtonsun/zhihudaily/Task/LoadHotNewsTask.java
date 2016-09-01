package com.example.axtonsun.zhihudaily.Task;

import android.os.AsyncTask;

import com.example.axtonsun.zhihudaily.Bean.HotStories;
import com.example.axtonsun.zhihudaily.Bean.Stories;
import com.example.axtonsun.zhihudaily.Net.HttpUtil;
import com.example.axtonsun.zhihudaily.Net.JsonHelper;
import com.example.axtonsun.zhihudaily.View.Adapter.RvAdapter;
import com.example.axtonsun.zhihudaily.View.Adapter.RvHotAdapter;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by AxtonSun on 2016/8/24.
 */
public class LoadHotNewsTask extends AsyncTask<Void, Void, List<HotStories>> {
    private RvHotAdapter adapter;


    public LoadHotNewsTask(RvHotAdapter adapter) {
        super();
        this.adapter = adapter;
    }
    @Override
    protected List<HotStories> doInBackground(Void... params) {
        List<HotStories> storiesList = null;
        try {
            storiesList = JsonHelper.parseJsonToHotList(HttpUtil.getLastNewsList());
        } catch (IOException | JSONException e) {

        } finally {
            return storiesList;
        }
    }
    @Override
    protected void onPostExecute(List<HotStories> storiesList) {
        adapter.refreshHotNewsList(storiesList);
    }

}