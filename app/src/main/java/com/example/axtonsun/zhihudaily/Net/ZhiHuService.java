package com.example.axtonsun.zhihudaily.Net;



import com.example.axtonsun.zhihudaily.Bean.Before;
import com.example.axtonsun.zhihudaily.Bean.DetailStories;
import com.example.axtonsun.zhihudaily.Bean.Hot;
import com.example.axtonsun.zhihudaily.Bean.Latest;
import com.example.axtonsun.zhihudaily.Bean.Stories;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by AxtonSun on 2016/11/29.
 */

public interface ZhiHuService {
    @GET("4/news/latest")
    Observable<Latest> getLatest();

    @GET("3/news/hot")
    Observable<Hot> getHot();

//    @GET("4/themes")
//    Observable<Themes> getThemes();
//
//    @GET("4/theme/{id}")
//    Observable<ThemeStories> getThemeList(@Path("id") int id);
//
    @GET("4/news/before/{date}")
    Observable<Before> getBefore(@Path("date") int date);

    @GET("4/news/{id}")
    Observable<DetailStories> getDetail(@Path("id") int id);


}
