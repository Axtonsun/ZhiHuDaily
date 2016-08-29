package com.example.axtonsun.zhihudaily.View.Fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.axtonsun.zhihudaily.Bean.Stories;
import com.example.axtonsun.zhihudaily.Task.LoadNewsTask;
import com.example.axtonsun.zhihudaily.Utility.Utility;
import com.example.axtonsun.zhihudaily.View.Adapter.RvAdapter;
import com.example.axtonsun.zhihudaily.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AxtonSun on 2016/8/19.
 */
public class RvList extends BaseFragment {

    private boolean isConnected;
    private RecyclerView mRecyclerView;
    private FloatingActionButton floatingActionButton;
    //private List<Stories> storiesList;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.viewpager_rv, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);//FloatingActionButton的Id
        floatingActionButton.setOnClickListener(new View.OnClickListener() {//FAB的点击事件
            @Override
            public void onClick(View v) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false));
        return view;
    }

    @Override
    protected void initData() {
        isConnected = Utility.checkNetworkConnection(mActivity);
        //storiesList = new ArrayList<>();
        RvAdapter adapter = new RvAdapter(mActivity);
        if (isConnected) new LoadNewsTask(adapter).execute();
        else Utility.noNetworkAlert(mActivity);
        mRecyclerView.setAdapter(adapter);
    }
}
