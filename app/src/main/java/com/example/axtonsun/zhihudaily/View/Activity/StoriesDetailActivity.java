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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.axtonsun.zhihudaily.Bean.Hot;
import com.example.axtonsun.zhihudaily.Bean.Stories;
import com.example.axtonsun.zhihudaily.DB.DailyZhDB;
import com.example.axtonsun.zhihudaily.R;
import com.example.axtonsun.zhihudaily.Utility.Utility;


/**
 * Created by AxtonSun on 2016/8/21.
 */
public class StoriesDetailActivity extends AppCompatActivity{

    public Toolbar toolbar;
    public ImageView imageView;
    public TextView detailBarTitle;
    public TextView detailBarCopyright;
    public WebView mWebView;

    public boolean isFavourite = false;
    public Stories stories;
    public Hot.RecentBean hotStories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_news);

        mWebView = (WebView) findViewById(R.id.webview);
        imageView = (ImageView) findViewById(R.id.detail_bar_image);
        detailBarTitle = (TextView) findViewById(R.id.detail_bar_title);
        detailBarCopyright = (TextView) findViewById(R.id.detail_bar_copyright);
        toolbar = (Toolbar) findViewById(R.id.tb1);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setWebView(mWebView);
        init();
    }

    public void init(){

    }

    private void setWebView(WebView mWebView) {
        mWebView.getSettings().setJavaScriptEnabled(true);//开启Javascript支持
        mWebView.setVerticalScrollBarEnabled(true);//设置竖直滚动条
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
    public static void startHotActivity(Context context, Hot.RecentBean hotStories) {
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
                    DailyZhDB.getInstance(this).saveFavourite(hotStories);
                    item.setIcon(R.drawable.fav_active);
                    isFavourite = true;
                } else {
                    DailyZhDB.getInstance(this).deleteFavourite(hotStories);
                    item.setIcon(R.drawable.fav_normal);
                    isFavourite = false;
                }
                break;
        }
        return true;
    }
}
