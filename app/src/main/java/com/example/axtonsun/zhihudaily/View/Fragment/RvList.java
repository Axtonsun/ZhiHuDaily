package com.example.axtonsun.zhihudaily.View.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.axtonsun.zhihudaily.Task.LoadBeforeNewsTask;
import com.example.axtonsun.zhihudaily.Task.LoadNewsTask;
import com.example.axtonsun.zhihudaily.Utility.Utility;
import com.example.axtonsun.zhihudaily.View.Adapter.RvAdapter;
import com.example.axtonsun.zhihudaily.R;

/**
 * Created by AxtonSun on 2016/8/19.
 */
public class RvList extends BaseFragment {

    private boolean isConnected;
    public RecyclerView mRecyclerView;
    private TextView textView;
    private FloatingActionButton floatingActionButton;
    private SwipeRefreshLayout swipeRefreshWidget;
    private RvAdapter adapter;
    private LinearLayoutManager layoutManager;
    private String date;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.viewpager_rv, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        textView = (TextView) view.findViewById(R.id.time_tv);
        swipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        swipeRefreshWidget.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorAccent,R.color.colorPrimary);//setColorSchemeResources():设置进度条的颜色主题，最多设置四种
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
        adapter = new RvAdapter(mActivity);
        swipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                       Looper.prepare();
                        if (isConnected) {
                            new LoadNewsTask(adapter).execute();
                            adapter.notifyDataSetChanged();
                            //Toast.makeText(mActivity, "刷新完成", Toast.LENGTH_SHORT).show();
                            swipeRefreshWidget.setRefreshing(false);
                        } else {
                            Utility.noNetworkAlert(mActivity);
                            swipeRefreshWidget.setRefreshing(false);//设置SwipeRefreshLayout当前是否处于刷新状态，一般是在请求数据的时候设置为true，在数据被加载到View中后，设置为false。
                        }
                        Looper.loop();
                    }
                }).start();
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 ==
                        adapter.getItemCount()) {
                    Log.e("TAG",lastVisibleItem + "lastVisibleItem " + adapter.getItemCount() + " count");
                    adapter.setMoreStatus(RvAdapter.LOADING_MORE);
    /*                new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            new LoadBeforeNewsTask(adapter);
                            adapter.setMoreStatus(RvAdapter.PULLUP_LOAD_MORE);
                            //adapter.notifyDataSetChanged();
                        }
                    }, 1500);*/
       /*             new Thread(new Runnable() {
                        @Override
                        public void run() {
                            new LoadBeforeNewsTask(adapter);
                            adapter.setMoreStatus(RvAdapter.PULLUP_LOAD_MORE);
                            adapter.notifyDataSetChanged();
                        }
                    }).start();*/
                   /* mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new LoadBeforeNewsTask(adapter);
                            adapter.setMoreStatus(RvAdapter.PULLUP_LOAD_MORE);
                        }
                    });*/
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();//返回当前最后一个可见Item的position
            }
        });
/*        if (isConnected) new LoadNewsTask(adapter).execute();
        else Utility.noNetworkAlert(mActivity);*/
        mRecyclerView.setAdapter(adapter);
    }
}
