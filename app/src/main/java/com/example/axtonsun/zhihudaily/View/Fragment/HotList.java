package com.example.axtonsun.zhihudaily.View.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.axtonsun.zhihudaily.Bean.Hot;
import com.example.axtonsun.zhihudaily.Bean.Latest;
import com.example.axtonsun.zhihudaily.Bean.Stories;
import com.example.axtonsun.zhihudaily.Net.RetrofitManager;
import com.example.axtonsun.zhihudaily.R;
import com.example.axtonsun.zhihudaily.Utility.Utility;
import com.example.axtonsun.zhihudaily.View.Adapter.RvHotAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AxtonSun on 2016/8/21.
 */
public class HotList extends BaseFragment{

    private boolean isConnected;
    private RecyclerView mRecyclerView;
    private RvHotAdapter adapter;
    private List<Hot.RecentBean> list;
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
        list = new ArrayList<>();
        adapter = new RvHotAdapter(mActivity,list);
    //    if (isConnected)
            loadHotNews();
    //    else Utility.noNetworkAlert(mActivity);
        mRecyclerView.setAdapter(adapter);
    }

    private void loadHotNews(){

        RetrofitManager.builder().getHot()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Hot>() {
                    @Override
                    public void onCompleted() {
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Hot recentBean) {
                        list.addAll(recentBean.getRecent());
                    }

                });
    }
}
