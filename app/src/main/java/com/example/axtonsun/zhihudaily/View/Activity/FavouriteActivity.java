package com.example.axtonsun.zhihudaily.View.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.example.axtonsun.zhihudaily.Bean.Hot;
import com.example.axtonsun.zhihudaily.Bean.Stories;
import com.example.axtonsun.zhihudaily.DB.DailyZhDB;
import com.example.axtonsun.zhihudaily.R;
import com.example.axtonsun.zhihudaily.View.Adapter.RvAdapter;
import com.example.axtonsun.zhihudaily.View.Adapter.RvHotAdapter;

import java.util.List;

/**
 * Created by AxtonSun on 2016/8/23.
 */
public class FavouriteActivity extends AppCompatActivity {

    private RvHotAdapter adapter;
    private List<Hot.RecentBean> storiesList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourite_rv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.fav_tb);
        toolbar.setTitle(R.string.favourite);
        toolbar.setTitleTextColor(getResources().getColor(R.color.drawer_normal));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.fav_rv);
        storiesList = DailyZhDB.getInstance(this).loadHotFavourite();
        adapter = new RvHotAdapter(this,storiesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

}
