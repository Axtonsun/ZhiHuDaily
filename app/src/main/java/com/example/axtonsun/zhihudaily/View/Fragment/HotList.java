package com.example.axtonsun.zhihudaily.View.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.axtonsun.zhihudaily.R;
import com.example.axtonsun.zhihudaily.Task.LoadHotNewsTask;
import com.example.axtonsun.zhihudaily.Task.LoadNewsTask;
import com.example.axtonsun.zhihudaily.Utility.Utility;
import com.example.axtonsun.zhihudaily.View.Adapter.RvAdapter;
import com.example.axtonsun.zhihudaily.View.Adapter.RvHotAdapter;

/**
 * Created by AxtonSun on 2016/8/21.
 */
public class HotList extends BaseFragment{

    private boolean isConnected;
    private RecyclerView mRecyclerView;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.viewpager_hot, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_hot);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false));
        return view;
    }

    @Override
    protected void initData() {
        isConnected = Utility.checkNetworkConnection(mActivity);
        //storiesList = new ArrayList<>();

        RvHotAdapter adapter = new RvHotAdapter(mActivity);
        if (isConnected) new LoadHotNewsTask(adapter).execute();
        else Utility.noNetworkAlert(mActivity);
        mRecyclerView.setAdapter(adapter);
    }
}
