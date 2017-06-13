package com.example.axtonsun.zhihudaily.View.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.axtonsun.zhihudaily.Bean.Latest;
import com.example.axtonsun.zhihudaily.Bean.Stories;
import com.example.axtonsun.zhihudaily.Net.RetrofitManager;
import com.example.axtonsun.zhihudaily.Utility.Utility;
import com.example.axtonsun.zhihudaily.View.Adapter.RvAdapter;
import com.example.axtonsun.zhihudaily.R;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AxtonSun on 2016/8/19.
 */
public class RvList extends BaseFragment {

    private boolean isConnected;
    public RecyclerView mRecyclerView;
    private FloatingActionButton floatingActionButton;
    private SwipeRefreshLayout swipeRefreshWidget;
    private RvAdapter adapter;
    private LinearLayoutManager layoutManager;

    private List<Stories> list;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.viewpager_rv, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);

        swipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        swipeRefreshWidget.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);//setColorSchemeResources():设置进度条的颜色主题，最多设置四种
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);//FloatingActionButton的Id
        floatingActionButton.setOnClickListener(new View.OnClickListener() {//FAB的点击事件
            @Override
            public void onClick(View v) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        });
        layoutManager = new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    protected void initData() {
        isConnected = Utility.checkNetworkConnection(mActivity);
        list = new ArrayList<>();
        adapter = new RvAdapter(mActivity,list);
        swipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isConnected = Utility.checkNetworkConnection(mActivity);
                if (isConnected) {
                    loadLatestNews();
//                    mRecyclerView.setAdapter(adapter);
//                  adapter.notifyDataSetChanged();
                   // notifyDataSetChanged方法是刷新Adapter中的数据，setAdapter是给控件设置适配器，让得到的数据在控件中显示出来，两个方法的作用是不一样的
                    Snackbar.make(mActivity.getWindow().getDecorView(),"已是最新消息",Snackbar.LENGTH_SHORT).show();
                    swipeRefreshWidget.setRefreshing(false);
                }else {
                    Snackbar.make(mActivity.getWindow().getDecorView(),"请检查网络",Snackbar.LENGTH_SHORT).show();
                    swipeRefreshWidget.setRefreshing(false);
                }
            }
        });
//        if (isConnected) {
            loadLatestNews();
//        }
//        else {
//            Utility.noNetworkAlert(mActivity);
//        }
//        mRecyclerView.setAdapter(adapter);
    }

    private void loadLatestNews(){
        RetrofitManager.builder().getLatest()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Latest>() {
                    @Override
                    public void onCompleted() {
//                        adapter.notifyDataSetChanged();
                        mRecyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Latest latest) {
                        list.addAll(latest.getStories());
                    }
                });
    }

    public List<Stories> getDownloadData(){
        return list;
    }

}
