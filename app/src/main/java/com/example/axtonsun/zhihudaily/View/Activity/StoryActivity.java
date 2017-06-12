package com.example.axtonsun.zhihudaily.View.Activity;

import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.axtonsun.zhihudaily.Bean.DetailStories;
import com.example.axtonsun.zhihudaily.Bean.Latest;
import com.example.axtonsun.zhihudaily.Bean.Stories;
import com.example.axtonsun.zhihudaily.DB.DailyZhDB;
import com.example.axtonsun.zhihudaily.Net.RetrofitManager;
import com.example.axtonsun.zhihudaily.Utility.HtmlUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AxtonSun on 2016/9/1.
 */
public class StoryActivity extends StoriesDetailActivity {

    @Override
    public void init() {
        stories = (Stories) getIntent().getSerializableExtra("stories");
        loadDNews();
        isFavourite = DailyZhDB.getInstance(StoryActivity.this).isFavourite(stories);
    }

    private void loadDNews(){
        RetrofitManager.builder().getDetail(stories.getId())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DetailStories>() {
                    @Override
                    public void onCompleted() {

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
