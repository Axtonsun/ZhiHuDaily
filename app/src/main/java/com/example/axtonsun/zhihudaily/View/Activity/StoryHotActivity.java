package com.example.axtonsun.zhihudaily.View.Activity;

import com.example.axtonsun.zhihudaily.Bean.HotStories;
import com.example.axtonsun.zhihudaily.DB.DailyZhDB;
import com.example.axtonsun.zhihudaily.Task.LoadNewsDetailTask;

/**
 * Created by AxtonSun on 2016/9/1.
 */
public class StoryHotActivity extends StoriesDetailActivity{
    @Override
    protected void init() {
        hotStories= (HotStories) getIntent().getSerializableExtra("hotStories");
        new LoadNewsDetailTask(mWebView).execute(hotStories.getId());
        //isFavourite = DailyZhDB.getInstance(StoryHotActivity.this).isFavourite(hotStories);
    }
}
