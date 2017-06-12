package com.example.axtonsun.zhihudaily.View.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.axtonsun.zhihudaily.R;
import com.example.axtonsun.zhihudaily.Utility.SampleHelper;

/**
 * Created by Axton on 2017/6/9.
 */

public class AboutViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutview);
        SampleHelper.with(this).init().loadAbout();
    }
}
