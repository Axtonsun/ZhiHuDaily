package com.example.axtonsun.zhihudaily.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;

import com.example.axtonsun.zhihudaily.Bean.HotStories;
import com.example.axtonsun.zhihudaily.Bean.Stories;
import com.example.axtonsun.zhihudaily.DB.DailyZhDB;
import com.example.axtonsun.zhihudaily.Helper.DayNightHelper;
import com.example.axtonsun.zhihudaily.R;
import com.example.axtonsun.zhihudaily.Task.LoadNewsDetailTask;
import com.example.axtonsun.zhihudaily.Utility.Utility;

/**
 * Created by AxtonSun on 2016/8/21.
 */
public class StoriesDetailActivity extends AppCompatActivity{

    protected WebView mWebView;
    protected boolean isFavourite = false;
    protected Stories stories;
    protected HotStories hotStories;
    private DayNightHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        helper = new DayNightHelper(this);
        if (helper.isDay()){
            setTheme(R.style.DayTheme);
        }else {
            setTheme(R.style.NightTheme);
        }
        setContentView(R.layout.item_news);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb1);
        toolbar.setTitle("享受阅读的乐趣");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mWebView = (WebView) findViewById(R.id.webview);
        setWebView(mWebView);
        init();
        /*stories = (Stories) getIntent().getSerializableExtra("stories");
        new LoadNewsDetailTask(mWebView).execute(stories.getId());//构造方法中WebView  第一个参数Integer型
        isFavourite = DailyZhDB.getInstance(StoriesDetailActivity.this).isFavourite(stories);*/

       /* pager = (ViewPager) findViewById(R.id.viewpager);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    stories = (Stories) getIntent().getSerializableExtra("stories");
                    new LoadNewsDetailTask(mWebView).execute(stories.getId());
                    isFavourite = DailyZhDB.getInstance(StoriesDetailActivity.this).isFavourite(stories);

                }else if (position == 1){
                    hotStories= (HotStories) getIntent().getSerializableExtra("hotstories");
                    new LoadNewsDetailTask(mWebView).execute(hotStories.getId());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/
    }

    protected void init(){

    }

    private void setWebView(WebView mWebView) {
        mWebView.getSettings().setJavaScriptEnabled(true);//开启Javascript支持
        mWebView.setVerticalScrollBarEnabled(true);//设置竖直滚动条
        //mWebView.setHorizontalScrollBarEnabled(false);
    }

    public static void startActivity(Context context, Stories stories) {
        if (Utility.checkNetworkConnection(context)) {
            Intent i = new Intent(context, StoryActivity.class);
            i.putExtra("stories", stories);
            context.startActivity(i);
        } else {
            Utility.noNetworkAlert(context);
        }
    }
    public static void startHotActivity(Context context, HotStories hotStories) {
        if (Utility.checkNetworkConnection(context)) {
            Intent i = new Intent(context, StoryHotActivity.class);
            i.putExtra("hotStories", hotStories);
            context.startActivity(i);
        } else {
            Utility.noNetworkAlert(context);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if (isFavourite) menu.findItem(R.id.action_favourite).setIcon(R.drawable.fav_active);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:

                break;
            case R.id.action_favourite:
                if (!isFavourite) {
                    DailyZhDB.getInstance(this).saveFavourite(stories);
                    item.setIcon(R.drawable.fav_active);
                    isFavourite = true;
                } else {
                    DailyZhDB.getInstance(this).deleteFavourite(stories);
                    item.setIcon(R.drawable.fav_normal);
                    isFavourite = false;
                }
                break;
        }
        return true;
    }
}
