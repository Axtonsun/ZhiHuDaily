package com.example.axtonsun.zhihudaily.View.Activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;

import com.example.axtonsun.zhihudaily.Bean.Stories;
import com.example.axtonsun.zhihudaily.DB.DailyZhDB;
import com.example.axtonsun.zhihudaily.Helper.DayNightHelper;
import com.example.axtonsun.zhihudaily.R;
import com.example.axtonsun.zhihudaily.View.Adapter.RvAdapter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by AxtonSun on 2016/8/23.
 */
public class FavouriteActivity extends AppCompatActivity {

    private RvAdapter adapter;
    private List<Stories> storiesList;
    private RecyclerView recyclerView;
    private DayNightHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new DayNightHelper(this);
        if (helper.isDay()){
            setTheme(R.style.DayTheme);
        }else {
            setTheme(R.style.NightTheme);
        }
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.favourite_rv);
        Toolbar toolbar= (Toolbar) findViewById(R.id.fav_tb);
        toolbar.setTitle(R.string.favourite);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.fav_rv);
        storiesList = DailyZhDB.getInstance(this).loadFavourite();
        adapter = new RvAdapter(this,storiesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

}
