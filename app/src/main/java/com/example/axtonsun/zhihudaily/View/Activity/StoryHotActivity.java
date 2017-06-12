package com.example.axtonsun.zhihudaily.View.Activity;

import com.bumptech.glide.Glide;
import com.example.axtonsun.zhihudaily.Bean.DetailStories;
import com.example.axtonsun.zhihudaily.Bean.Hot;
import com.example.axtonsun.zhihudaily.DB.DailyZhDB;
import com.example.axtonsun.zhihudaily.Net.RetrofitManager;
import com.example.axtonsun.zhihudaily.Utility.HtmlUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AxtonSun on 2016/9/1.
 */
public class StoryHotActivity extends StoriesDetailActivity{
    @Override
    public void init() {
        hotStories= (Hot.RecentBean) getIntent().getSerializableExtra("hotStories");
        loadHotNews();
        isFavourite = DailyZhDB.getInstance(StoryHotActivity.this).isHotFavourite(hotStories);
    }

    private void loadHotNews(){
        RetrofitManager.builder().getDetail(hotStories.getNews_id())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DetailStories>() {
                    @Override
                    public void onCompleted() {
//                        adapter.notifyDataSetChanged();

                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onNext(DetailStories latest) {
                        String imgUrl = latest.getImage();
                        Glide.with(getApplicationContext()).load(imgUrl).into(imageView);
                        detailBarTitle.setText(latest.getTitle());
                        detailBarCopyright.setText(latest.getImage_source());
                        String htmlData = HtmlUtil.createHtmlData(latest.getBody(), latest.getCss(), latest.getJs());
                        mWebView.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
                    }
                });
    }
}
