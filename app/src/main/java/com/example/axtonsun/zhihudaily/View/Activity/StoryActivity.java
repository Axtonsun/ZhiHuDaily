package com.example.axtonsun.zhihudaily.View.Activity;

import android.os.Bundle;

import com.example.axtonsun.zhihudaily.Bean.Stories;
import com.example.axtonsun.zhihudaily.DB.DailyZhDB;
import com.example.axtonsun.zhihudaily.Task.LoadNewsDetailTask;

/**
 * Created by AxtonSun on 2016/9/1.
 */
public class StoryActivity extends StoriesDetailActivity {

    @Override
    protected void init() {
        stories = (Stories) getIntent().getSerializableExtra("stories");
        new LoadNewsDetailTask(mWebView).execute(stories.getId());
        isFavourite = DailyZhDB.getInstance(StoryActivity.this).isFavourite(stories);
    }
}
